FROM node:8
RUN mkdir /code
WORKDIR /code
ADD . /code/
CMD["npm","install"]
EXPOSE 3000
