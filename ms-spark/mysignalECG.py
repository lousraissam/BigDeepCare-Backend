from operator import mod
from re import I
import findspark
findspark.init()
from pyspark import SparkContext, SparkConf
from pyspark.streaming import StreamingContext
from pyspark.sql import SparkSession
from pyspark.streaming.kafka import KafkaUtils
from uuid import uuid1
import faulthandler
import numpy as np
from pyspark.sql.types import StructType, StructField, StringType
import influxdb_client
from influxdb_client import Point
from influxdb_client.client.write_api import SYNCHRONOUS

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
    bucket="ecg_mysignal"

    write_api = client.write_api(write_options=SYNCHRONOUS)
    
    point = (
      Point("ecg_mysignal")
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
    ssc = StreamingContext(sc, 1) # 1 second window
    
    #kstream = KafkaUtils.createDirectStream(ssc, topics = ['ecg'], kafkaParams = {"metadata.broker.list":"localhost:9092"})
    kafka_topic_ECG = "ecg"
    kafka_topic_TEMP = "temp"
    kafka_topic_BP = "bp"
    kafka_bootstrap_servers = 'localhost:9092'
    zk_bootstrap_servers = 'localhost:2181'
    schema = StructType([StructField("timestamp", StringType(), True), StructField("value", StringType(), True),])

    
    kvsE = KafkaUtils.createDirectStream(ssc, [kafka_topic_ECG], {'bootstrap.servers':kafka_bootstrap_servers, 'group.id':'group1'})
    kvs2E = KafkaUtils.createDirectStream(ssc, [kafka_topic_ECG], {'bootstrap.servers':kafka_bootstrap_servers, 'group.id':'group2'})
    kvsTEMP = KafkaUtils.createDirectStream(ssc, [kafka_topic_TEMP], {'bootstrap.servers':kafka_bootstrap_servers, 'group.id':'group3'})
    kvsBP = KafkaUtils.createDirectStream(ssc, [kafka_topic_BP], {'bootstrap.servers':kafka_bootstrap_servers, 'group.id':'group4'})
    
    kvsE.foreachRDD(lambda x: getrdds(x))
    kvs2E.foreachRDD(lambda x: getrdds(x))
    kvsTEMP.foreachRDD(lambda x: getTemprdds(x))
    kvsBP.foreachRDD(lambda x: getBPrdds(x))

    def getrdds(rdd):
        if not rdd.isEmpty():      
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
      print('in rdd TEMP', tab)
      values = tab[1]
      loadTo_influx_TEMP(values, tab[0])
    
    def do_job_BP(tab):
      print('in rdd bp', tab)
      values = tab[1]
      loadTo_influx_BP(values, tab[0])

      return tab

    def do_job(tab):
      print('in rdd ecg', tab)
      values = tab[1]
      
      values = values + '|' + "" + ','+ ""
      loadTo_influx(values, tab[0])

      return tab

    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
    sc.stop()