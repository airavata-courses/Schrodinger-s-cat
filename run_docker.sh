#!/bin/sh

#docker stack rm login_authenticaion_service
#docker swarm leave --force

docker rm -f login_authenticaion_container

docker rmi nawazkh/maven:3.5.4
echo "---------removed old containers and images if existed---------"
docker build -f Dockerfile -t nawazkh/maven:3.5.4 .
echo "---------built docker image for maven ---------"
cd restful-user-authentication/

docker run --rm -v "$PWD":/app -w /app --network host \
-p 8888:8888 \
nawazkh/maven:3.5.4 mvn clean install
echo "--------- created the jar for the project ---------"
#docker run -d -it -v "$PWD":/app -w /app --network host \
#--name login_authenticaion_container -p 8888:8888 \
#nawazkh/maven:3.5.4 \
#java -jar target/restful-user-authentication-0.0.1-SNAPSHOT.jar
echo "--------- starting the login module ---------"
docker-compose up -d
#cd ..
#docker swarm init
#docker stack deploy -c docker-compose.yml login_authenticaion_service
