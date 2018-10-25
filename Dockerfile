from rabbitmq:3-management
RUN mkdir /code
WORKDIR /code
ENV RABBITMQ_ERLANG_COOKIE 'secret_cookie'
CMD ["rabbitmq-server"]