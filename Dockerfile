FROM node:8
RUN mkdir /code
WORKDIR /code
ADD . /code/
EXPOSE 3000
