#!/bin/sh

#docker stack rm login_authenticaion_service
#docker swarm leave --force
cd restful-user-authentication/

# echo "---------removed old containers and images if existed---------"
sudo docker swarm init
sudo docker stack deploy -c docker-stack-postgresql.yml post

sudo docker rmi nawazkh/maven:3.5.4
sudo docker rmi nawazkh/login:latest
sudo docker build -f Dockerfile.maven -t nawazkh/maven:3.5.4 .
sudo docker run --rm -v "$PWD":/app -w /app --network host -p 8888:8888 nawazkh/maven:3.5.4 clean install
#---------#
# echo "--------- created the jar for the project ---------"
#docker run -d -it -v "$PWD":/app -w /app --network host \
#--name login_authenticaion_container -p 8888:8888 \
#nawazkh/maven:3.5.4 \
#java -jar target/restful-user-authentication-0.0.1-SNAPSHOT.jar
#---------#
sudo docker build -f Dockerfile -t nawazkh/login:latest .
# docker-compose up -d
#---------#
# echo "--------- login module up ---------"
#cd ..
# docker swarm init
# docker stack deploy -c docker-compose.yml login_authenticaion_service
#---------#
sudo docker stack deploy -c docker-compose.yml login_authenticaion_service
