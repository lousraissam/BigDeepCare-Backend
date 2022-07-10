import json
import re
import time
from kafka import KafkaProducer
from scipy.misc import electrocardiogram

producer = KafkaProducer(bootstrap_servers='localhost:9092')

def getNSignal():
    ecg = electrocardiogram()
    ecg_n = ecg[360:720]
    return ecg_n

def getVSignal():
    ecg = electrocardiogram()
    ecg_v = ecg[16884:17244] #premature ventricular contraction
    return ecg_v


while True: 

    record1 = getNSignal()
    record2 = getVSignal()

    producer.send("ecg", json.dumps(record1.tolist()).encode(), key =b'p1')  #topic: velib-stations #key specifices which partition
    
    producer.send("ecg", json.dumps(record2.tolist()).encode(), key =b'p2')
    
    print("Produced {} records".format(len(record2)*2))

    time.sleep(1)