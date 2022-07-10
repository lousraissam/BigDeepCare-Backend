import paho.mqtt.client as mqtt
from pykafka import KafkaClient
from kafka import KafkaProducer

import time
import json
import influxdb_client
from influxdb_client import Point
from influxdb_client.client.write_api import SYNCHRONOUS

mqtt_broker = "172.20.10.9"
mqtt_client = mqtt.Client("BridgeMQTT2Kafka")
mqtt_client.connect(mqtt_broker)

# kafka_client = KafkaClient(hosts="localhost:9092")
kafka_client = KafkaProducer(bootstrap_servers='localhost:9092')
# kafka_topic = kafka_client.topics['ecg']
# kafka_temp = kafka_client.topics['temp']
# kafka_bp = kafka_client.topics['bp']

# kafka_producer_temp = kafka_temp.get_sync_producer()
# kafka_producer = kafka_topic.get_sync_producer()
# kafka_producer_bp = kafka_bp.get_sync_producer()


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

def on_message(client, userdata, message):
    msg_payload = message.payload
    # msg_payload = json.loads(msg_payload)
    print("Received MQTT message: ", msg_payload)
    msg_payload = json.loads(msg_payload)
    type = msg_payload["type"]
    value=msg_payload["value"]
    
    print("Received MQTT message to json message: ", value)
    print("type: ", type)
    print("value: ", value)

    if(type=="temp"):
        # print('producing in temp**')
        # value = {"temperature": value}
        # print('new value', value)
        loadTo_influx_TEMP(str(value), 'p1')
        # kafka_client.send('temp',json.dumps(value).encode('utf-8'),key =b'p1')
       
       

    elif(type=="ecg"):
        loadTo_influx(str(value), 'p1')
        # kafka_client.send("ecg",json.dumps(value).encode('utf-8'), key =b'p1')
        # print('producing in ecg**', type, value)
       

        
    elif(type=="bp"):
        loadTo_influx_BP(str(value), 'p1')

        

        # kafka_client.send("bp",json.dumps(value).encode('utf-8'), key =b'p1')
        # print('producing in bp**', type, value)

    

    # print(  msg_payload )

mqtt_client.loop_start()
mqtt_client.subscribe("temp")
mqtt_client.subscribe("ecg")
mqtt_client.subscribe("bp")
mqtt_client.on_message = on_message




time.sleep(100)
mqtt_client.loop_end()
