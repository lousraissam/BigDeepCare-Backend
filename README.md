# BigDeepCare-Backend
Microservices architecture using spring boot and PySpark to make preprocessing and classification of data streams(ECG signals)

![Global architecture](https://user-images.githubusercontent.com/64719616/182228615-46cf0f08-9fe3-4293-bb44-5fc294a3ae5d.png)

# Describe architecture

• Prepare environment in Arduino by installing MySignals library and uploading an arduino C code to capture ECG, Temperature and blood pressure from sensors then writing this data in serial.
• Integrate MySignals with Arduino Uno in all input/output.
• Preparing environment in RaspBerry Pi by installing requirements like MQTT paho...
• Power Arduino by related with RaspBerry via cable.
• The Raspberry Pi reads data from the serial and assigns a timestamp with each received value to identify the exact time when the data was captured.
• After reading data,it is published in raspBerry Pi localhost using mosquitto-pub for different topics ECG, blood pressure and temperature.
• Make our machine and raspBerry Pi in the same network and use mosquitto-sub to subscribe in the IP address of RaspBerry pi for different topics
• Write a python script as an MQTT-Kafka-Bridge to make connection between mqtt subscriber and kafka producer by reading data from mosquitto-sub and produce it in topics.
• kafka consumer subscribe on topic published by kafka producer.
• Ms-pre-processing-classification receives data, preprocess it using PySpark,and make the prediction of the ECG arrhythmia type using our proposed LSTM model then insert results into the influx DB database.
