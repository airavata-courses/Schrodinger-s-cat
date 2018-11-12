FROM python:3
ENV PYTHONUNBUFFERED 1
RUN mkdir /code
WORKDIR /code
ADD requirements.txt /code/
RUN pip install -r requirements.txt
ADD . /code/
CMD python3 pythonMicroservice/manage.py makemigrations; python3 pythonMicroservice/manage.py migrate;python3 pythonMicroservice/manage.py runserver 0.0.0.0:8000