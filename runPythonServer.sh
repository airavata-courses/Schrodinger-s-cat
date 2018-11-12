echo "Starting"
python3 pythonMicroservice/manage.py makemigrations
python3 pythonMicroservice/manage.py migrate
echo "About to run server"
nohup bash -c "python3 pythonMicroservice/manage.py runserver >myscrip.log 2>&1 &"
echo "Server is running"