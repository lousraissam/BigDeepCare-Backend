FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/dm-ms.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]