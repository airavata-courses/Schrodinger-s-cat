# Using an official Java Runtime as a parent image
# choosing
FROM openjdk:8-jdk-alpine
LABEL description="Docker file for login auth jar"
LABEL maintainer="Nawaz Hussain Khazielakha"
LABEL email="k.nawaz.h@gmail.com"
EXPOSE 8888
ADD /target/restful-user-authentication-0.0.1-SNAPSHOT.jar login.jar
ENTRYPOINT ["java","-jar","login.jar","--spring.config.name=deploy","--spring.config.location=classpath:/prodConfigs/"]
