FROM python:3
ENV PYTHONUNBUFFERED 1
RUN mkdir /code
WORKDIR /code
ADD pipinstalls.txt /code/
RUN pip install -r pipinstalls.txt
ADD . /code/

CMD python3 pythonMicroservice/manage.py makemigrations; python3 pythonMicroservice/manage.py migrate;echo "from django.contrib.auth.models import User; User.objects.create_superuser('djangoAdmin', 'admin@example.com', 'djangoAdmin')" | ./pythonMicroservice/manage.py shell;python3 pythonMicroservice/manage.py runserver 0.0.0.0:8000;
