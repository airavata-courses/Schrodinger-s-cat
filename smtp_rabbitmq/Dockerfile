# Using an official Java Runtime as a parent image
# choosing
FROM openjdk:8-jdk-alpine
LABEL description="Docker file for login auth jar"
LABEL maintainer="Nawaz Hussain Khazielakha"
LABEL email="k.nawaz.h@gmail.com"
ADD /target .
ADD /target/smtp_rabbitmq.jar smtp_consumer.jar
ENTRYPOINT ["java","-jar","smtp_consumer.jar"]
