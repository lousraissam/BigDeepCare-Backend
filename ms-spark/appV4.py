from operator import mod
from re import I
import re
import sys
import findspark
findspark.init()
from pyspark import SparkContext, SparkConf
from pyspark.streaming import StreamingContext
from pyspark.sql import SparkSession
from pyspark.streaming.kafka import KafkaUtils
from uuid import uuid1
import faulthandler
from ecgdetectors import Detectors
import numpy as np
import tensorflow.keras as keras
from pyspark.sql.types import StructType, StructField, StringType
from datetime import datetime
import influxdb_client
from influxdb_client import Point
from influxdb_client.client.write_api import SYNCHRONOUS
import pywt
from pywt import wavedec

def getSignalInterval(data_signal,data_peaks):
  #a = np.zeros(shape=(len(data_peaks),319)) many signals
  r=[]
  for i in range(len(data_peaks)):
    index = data_peaks[i] 
    value = data_signal[index]
    r.append(value)
    

  #get the highest peak
  rp = np.argmax(r)
  rp = data_peaks[rp]
  print("peak at :", rp)
  #rp : highest point index
  start = rp-153
  end = rp+166
  print(start,end)

  if(start<0):
    start = rp-120
    end = rp + 199
  elif(end>len(data_signal)):
    start = rp-169
    end = rp + 150

  if(start>=0 and end<=len(data_signal)):
    data_signal = data_signal[start:end]
  
  print(len(data_signal))

  return data_signal
#--------------------------------------------------------------------------------
def conf_r_peak_Detector(input):

    BASIC_SRATE = 319
    signal_pad_samples = 10
    signal_pad = np.zeros(signal_pad_samples) # pad one sec to detect initial peaks properly
    signalf = input
    signalf = signalf[:350]
    detectors = Detectors(BASIC_SRATE)

    detectors = {
                #'pan_tompkins_detector':[detectors.pan_tompkins_detector, []],
                'hamilton_detector':[detectors.hamilton_detector, []],
                #'christov_detector':[detectors.christov_detector, []]#,
                #'engzee_detector':[detectors.engzee_detector, []],
                #'swt_detector':[detectors.swt_detector, []],
                #'two_average_detector':[detectors.two_average_detector, []],
                }
    for kd in detectors.keys():
        vd = detectors[kd]
        r_peaks = np.array(vd[0](np.hstack((signal_pad,signalf)))) - signal_pad_samples
        vd[1] = r_peaks

    data_peaks = detectors['hamilton_detector'][1]
    print(data_peaks)
    data_signal = getSignalInterval(signalf,data_peaks)
    return data_signal
#----------------------------------------------------------------------------------
def denoise_signal(X, dwt_transform, dlevels, cutoff_low, cutoff_high):
  coeffs = wavedec(X, dwt_transform, level=dlevels)   # wavelet transform 'bior4.4'
  # scale 0 to cutoff_low 
  for ca in range(0,cutoff_low):
    coeffs[ca]=np.multiply(coeffs[ca],[0.0])
  # scale cutoff_high to end
  for ca in range(cutoff_high, len(coeffs)):
    coeffs[ca]=np.multiply(coeffs[ca],[0.0])
  Y = pywt.waverec(coeffs, dwt_transform) # inverse wavelet transform
  return Y  
#----------------------------------------------------------------------------------
def normalize(input):
  maxE = np.amax(input)
  minE = np.amin(input)
  input = (input + minE*(-1))/(maxE-minE)

  return input
#----------------------------------------------------------------------------------
def preprocess(ecg):
    #denose data
    ecg = denoise_signal(ecg,'bior4.4', 7, 1 , 6)#<--- trade off - the less the cutoff - the more R-peak morphology is lost
    #normilaze data
    ecg= normalize(ecg)
    #get signal interval (close to r_peak)
    my_data = conf_r_peak_Detector(ecg)
    my_data = my_data.reshape(1,my_data.shape[0],1)
    return my_data
#----------------------------------------------------------------------------
def build_model(input_shape):
  """Generates RNN-LSTM model
  :param input_shape (tuple): Shape of input set
  :return model: RNN-LSTM model
  """

  # build network topology
  model = keras.Sequential()

  # 2 LSTM layers
  model.add(keras.layers.LSTM(128, input_shape=input_shape, return_sequences=True))
  model.add(keras.layers.LSTM(64))

  #dropout
  model.add(keras.layers.Dropout(0.5)) # avoid overfitting 

  # dense layer
  model.add(keras.layers.Dense(64, activation='relu'))

  # output layer
  model.add(keras.layers.Dense(6, activation='softmax'))

  return model
#-----------------------------------------------------------
def ecg_type(typeC):
    switcher = {
        0: "(0-N) Normal ",
        1: "(1-L) Left bundle branch block",
        2: "(2-R) Right bundle branch block",
        3: "(3-A) Atrial premature",
        4: "(4-V) Premature ventricular contraction",
        5: "(5-/) Paced",
    }
    return switcher.get(typeC, "")
#-------------------------------------------------------------------
def load_model():
    input_shape = (319,1)
    model = build_model(input_shape) 
    model.load_weights('best_model.h5')
    return model
#---------------------------------------------------------------------------------
def classify(model, my_data):
    classification = model.predict(my_data)*100
    purcentC = "{:.2f}".format(np.amax(classification))
    typeC = np.argmax(classification)
    typeC = ecg_type(typeC)
    prediction = [typeC,purcentC]
    return prediction
#--------------------------------------------------------------------
def set_influx():
    # You can generate an API token from the "API Tokens Tab" in the UI
    token = "H417NO73jCvZXl9cInFHAjZp1v8fq2A3GcqENXMC2YCekgfmKlywL2qDcbSjmWg5BVb8nuC61R9lxVhYMscZLQ=="
    org = "esi-sba"
    url = "http://localhost:8086"

    client = influxdb_client.InfluxDBClient(url=url, token=token, org=org)
    return client

def loadTo_influx(values, patient):
    client = set_influx()
    #Write Data-------------------------------------------------------------------
    bucket="ecg"

    write_api = client.write_api(write_options=SYNCHRONOUS)
    
    point = (
      Point("ecg")
      .tag("patient_id", patient)
      .field("record", values)
    )
    write_api.write(bucket=bucket, org="esi-sba", record=point)
    client.close()
#-----------------------------------------------------------------------------------
def loadTo_influx_BP(value, patient):
    client = set_influx()
    #Write Data-------------------------------------------------------------------
    bucket="ecg"

    write_api = client.write_api(write_options=SYNCHRONOUS)
    
    point = (
      Point("bp")
      .tag("patient_id", patient)
      .field("record", value)
    )
    write_api.write(bucket=bucket, org="esi-sba", record=point)
    client.close()
#-----------------------------------------------------------------------------------
def loadTo_influx_TEMP(value, patient):
    client = set_influx()
    #Write Data-------------------------------------------------------------------
    bucket="ecg"

    write_api = client.write_api(write_options=SYNCHRONOUS)
    
    point = (
      Point("temp")
      .tag("patient_id", patient)
      .field("record", value)
    )
    write_api.write(bucket=bucket, org="esi-sba", record=point)
    client.close()
#-----------------------------------------------------------------------------------

if __name__ == "__main__":
    faulthandler.enable()
    sc = SparkContext(appName="kafka")
    spark = SparkSession.builder.getOrCreate()
    sc.setLogLevel("ERROR")
    ssc = StreamingContext(sc, 1) # 2 second window
    
    #kstream = KafkaUtils.createDirectStream(ssc, topics = ['ecg'], kafkaParams = {"metadata.broker.list":"localhost:9092"})
    kafka_topic_ECG = "ecg"
    kafka_topic_TEMP = "temp"
    kafka_topic_BP = "bp"
    kafka_bootstrap_servers = 'localhost:9092'
    zk_bootstrap_servers = 'localhost:2181'
    schema = StructType([StructField("timestamp", StringType(), True), StructField("value", StringType(), True),])

    #kvs = KafkaUtils.createStream(ssc, zk_bootstrap_servers, 'spark-streaming-consumer', {kafka_topic_name:1}) 
    kvsE = KafkaUtils.createDirectStream(ssc, [kafka_topic_ECG], {'bootstrap.servers':kafka_bootstrap_servers, 'group.id':'group1'})
    kvs2E = KafkaUtils.createDirectStream(ssc, [kafka_topic_ECG], {'bootstrap.servers':kafka_bootstrap_servers, 'group.id':'group2'})
    kvsTEMP = KafkaUtils.createDirectStream(ssc, [kafka_topic_TEMP], {'bootstrap.servers':kafka_bootstrap_servers, 'group.id':'group3'})
    kvsBP = KafkaUtils.createDirectStream(ssc, [kafka_topic_BP], {'bootstrap.servers':kafka_bootstrap_servers, 'group.id':'group4'})
    #kvs = KafkaUtils.createDirectStream(ssc, [kafka_topic_name], {'bootstrap.servers':kafka_bootstrap_servers,'group.id':'test-group','auto.offset.reset':'largest'})

    
    
    
    
    #words = sc.textFile("C:\Spark\spark-2.4.8-bin-hadoop2.7\kafka.txt").
    #wordCounts = words.map(lambda word: (word, 1)).reduceByKey(lambda a,b:a +b)
    
    #vals = kvs.flatMap(lambda line: line[1].split(","))
    # save lines to file
    #df = spark.createDataFrame(rdd, schema=schema)
    
    
    kvsE.foreachRDD(lambda x: getrdds(x))
    kvs2E.foreachRDD(lambda x: getrdds(x))
    kvsTEMP.foreachRDD(lambda x: getTemprdds(x))
    kvsBP.foreachRDD(lambda x: getBPrdds(x))

    def getrdds(rdd):
        if not rdd.isEmpty():      
            #v = rdd.values().cache().first()
            rdd.foreach(do_job)
        return rdd
    
    def getTemprdds(rdd):
        if not rdd.isEmpty():      
            rdd.foreach(do_job_TEMP)
        return rdd
    
    def getBPrdds(rdd):
        if not rdd.isEmpty():      
            rdd.foreach(do_job_BP)
        return rdd
    
    def do_job_TEMP(tab):
      values = tab[1]
      loadTo_influx_TEMP(values, tab[0])
    
    def do_job_BP(tab):
      values = tab[1]
      loadTo_influx_BP(values, tab[0])

      return tab

    def do_job(tab):
      start_time = datetime.now() #--------------------------------
      values = tab[1]
      #remove brackets
      a = values[1:-1]
      #convert a to array of strings
      x = a.split(',')
      #convert to array of floats
      y = np.array(x, dtype=np.float32)
      second_time = datetime.now() #--------------------------------

      my_data = preprocess(y)
      third_time = datetime.now()#----------------------------------

      #load model
      model = load_model()
      fourth_time = datetime.now()#---------------------------------
      #model prediction
      prediction = classify(model, my_data)
      fifth_time = datetime.now()#---------------------------------
      
      values = values + '|' + prediction[0] + ','+ prediction[1]
      loadTo_influx(values, tab[0])
      end = datetime.now()#-----------------------------------------

      print(prediction,'-------------------------------------------------') 
      print('Duration get_data: {}'.format(second_time - start_time))
      print('Duration preprocessing: {}'.format(third_time - second_time))
      print('Duration load model: {}'.format(fourth_time - third_time))
      print('Duration predict: {}'.format(fifth_time - fourth_time))
      print('Duration load to influx: {}'.format(end - fifth_time))
      print('Duration total: {}'.format(end - start_time))
      

      return tab

    
    ssc.start()
    # stream will run for 50 sec
    #ssc.awaitTerminationOrTimeout(50)
    ssc.awaitTermination()
    ssc.stop()
    sc.stop()