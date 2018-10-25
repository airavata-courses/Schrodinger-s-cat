from rabbitmq:3-management
RUN mkdir /code
WORKDIR /code
ENV RABBITMQ_ERLANG_COOKIE 'secret_cookie'
EXPOSE 15672
EXPOSE 5672
EXPOSE 4369
EXPOSE 1883
CMD ["rabbitmq-server"]
