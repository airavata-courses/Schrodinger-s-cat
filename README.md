# Schrodinger-s-cat

Wiki Link : https://github.com/airavata-courses/Schrodinger-s-cat/wiki

## foodcode
An interactive website where you can browse for recipes, upload your own creations and also check-out what your friends are cooking! Get upvotes, suggestions and recommendations. Just Sign up!

### About this branch
* Master Branch.
* Stable Releases
* Team members: [Nawaz](https://www.linkedin.com/in/nawazhk/), [Prashanth](https://www.linkedin.com/in/prashanth-swargam-pswargam/) and [Soumya](https://www.linkedin.com/in/jlsoumya/)

### Active Branches
* feature-login_authenticatiion : Login Authentication to be implemented in Spring
* feature-nodejs_server : NodeJS server to handle incoming and outgoing requests to and fro from the front end. Also communicate with the Messaging Queue and gather distributed logs.
* feature-react_ui : Front end of the project
* feature-search_recipes : To be implemented in python.

### Execute the following steps to start the project

## Step -1:
* Clone the github repository
* Change the branch to feature-nodejs_server using the following command   
    git checkout feature-nodejs_server
* Change the directory to Schrodinegr's - cat
* Check if you have the file "package.json"
* Run the following commands
   * npm install 
   * npm install --save-dev mocha
   * npm install -g chai
   * npm start


## Step -2:
* Clone the github repository
* Change the branch to frontEnd_React using the following command   
    * git checkout frontEnd_React
* Change the directory to Schrodinegr's - cat
* Check if you have the file "package.json"
* Run the following commands
   * npm install 
   * npm install --save-dev mocha
   * npm install -g chai
   * npm start


## Step -3:
* Host machine should have python 3.6 or higher. Follow the below instructions to check your python version
    * Include the you python installation directory in path variable
        * SET PATH=$PATH:<PYTHON INSTALLATION DIR>
        * give the command "python". It should open a python shell and display version
* Clone the github repository
* Change the branch to feature-search_recipes using the following command   
    * git checkout feature-search_recipes
* Change the directory to Schrodinegr's - cat
* Check if you have the file "pythonMicroservice/manage.py"
* Run the following commands
   * pip install django
   * pip install pytz
   * pythonMicroservice/manage.py makemigrations
   * pythonMicroservice/manage.py migrate
   * pythonMicroservice/manage.py runserver