# Using an official Java Runtime as a parent image
# choosing
FROM openjdk:8-jdk-alpine
LABEL description="This a docker file to make maven executable"
LABEL maintainer="Nawaz Hussain Khazielakha"
LABEL email="k.nawaz.h@gmail.com"
# Install Maven
#RUN apk add --no-cache curl tar bash
RUN apk add curl tar bash
ARG MAVEN_VERSION=3.5.4
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
| tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
#speed uo Maven JVM a bt
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
CMD ["/usr/bin/mvn"]
