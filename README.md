# Schrodinger-s-cat 
[![Build Status](https://travis-ci.org/airavata-courses/Schrodinger-s-cat.svg?branch=feature-login_authenticatiion)](https://travis-ci.org/airavata-courses/Schrodinger-s-cat)

Wiki Link : https://github.com/airavata-courses/Schrodinger-s-cat/wiki

## foodcode
An interactive website where you can browse for recipes, upload your own creations and also check-out what your friends are cooking! Get upvotes, suggestions and recommendations. Just Sign up!

### About this branch
* Feature-Login-Authentication Branch.
* Team members: [Nawaz](https://www.linkedin.com/in/nawazhk/), [Prashanth](https://www.linkedin.com/in/prashanth-swargam-pswargam/) and [Soumya](https://www.linkedin.com/in/jlsoumya/)

<<<<<<< HEAD
### How to run this project? 
- You need Postman to test the functionality of this module. Download postman from https://www.getpostman.com.
- You also need to have an installed instance of MySQL in your local machine.
- Then run the following commands:
	- mysql_upgrade
  	- mysql -u root -e 'CREATE DATABASE IF NOT EXISTS loginAuth;'
  	- mysql -u root -e "CREATE USER IF NOT EXISTS 'nawaz'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';"
  	- mysql -u root -e "GRANT ALL PRIVILEGES ON loginAuth.* TO 'nawaz'@'localhost';"
- Then Check out/clone this repository.
- run 'mvn clean install' from restful-user-authentication folder to check if the checked out code will work or not.
- once mvn runs successfully, open this project in eclipse to launch the project.
- right click on RestfulUserAuthenticationApplication.java and run it as java application to launch it.
- you can hit each link in postman at http://localhost:8888/<below links> to observe the working of this module
- Or you can checkout all the branches and get the complete project working and start from sigining up as a user in this portal


#### What all added? 
- Swagger UI
- Customized Exception hanlding
- Dynamic Filtering 
- API Modeling
- Http Security
- Web Secutiry
- Test Cases to check user registration

### Links
- Need login.html at http://localhost:8888/login.html
- Need registration.html at http://localhost:8888/registration.html
- Need access-denied.html at http://localhost:8888/access-denied.html
- Need home.html in admin folder at http://localhost:8888/admin/access-denied.html

Need to register a user in registration.html and then access create and delete urls


#### Post (requires user authentication)
- http://localhost:8888/jpa/users/create							- createUser
- http://localhost:8888/jpa/users/create/{id}/save-posts			- createUserPosts

#### Get (require no authentication)
- http://localhost:8888/jpa/users/get/all-attributes 				- retrieveAllUsers 
- http://localhost:8888/jpa/users/get/f-l-name 					- retrieveAllUsersFirstLastName
- http://localhost:8888/jpa/users/get/uId-emailId 				- retrieveAllUsersUIdEmailId
- http://localhost:8888/jpa/users/get/fn-ln-ui-ps					- retrieveAllUsersFnLnUnPass
- http://localhost:8888/jpa/users/get/fn-ln-ds-re					- retrieveAllUsersFnLnDrRId
- http://localhost:8888/jpa/users/get/all-attributes/{id}			- retrieveUserAllAttributes
- http://localhost:8888/jpa/users/get/f-l-name/{id}				- retrieveUserFirstLastName
- http://localhost:8888/jpa/users/get/uId-emailId/{id}			- retrieveUserIdEmail
- http://localhost:8888/jpa/users/get/fn-ln-ui-ps/{id}			- retrieveFirstLastUserIdPass
- http://localhost:8888/jpa/users/get/fn-ln-ds-re/{id}			- retrieveFirstLastDescRecipeId

#### Delete (requires user authentication)
- http://localhost:8888/jpa/users/delete/{id}					- deleteUser
- http://localhost:8888/jpa/users/delete/{id}/delete-post			- deleteUserPost
=======
### Active Branches
* feature-login_authenticatiion : Login Authentication to be implemented in Spring
* feature-nodejs_server : NodeJS server to handle incoming and outgoing requests to and fro from the front end. Also communicate with the Messaging Queue and gather distributed logs.
* frontEnd-React : Front end of the project
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
        * SET PATH=$PATH:&lt;PYTHON INSTALLATION DIR&gt;
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

## Step -4:
* Clone the github repository
* Change the branch to feature-login_authentication using the following command   
    * git checkout feature-login_authentication
* Change the directory to Schrodinegr's - cat
* Then run the following commands:
	* mysql_upgrade
  	* mysql -u root -e 'CREATE DATABASE IF NOT EXISTS loginAuth;'
  	* mysql -u root -e "CREATE USER IF NOT EXISTS 'nawaz'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';"
  	* mysql -u root -e "GRANT ALL PRIVILEGES ON loginAuth.* TO 'nawaz'@'localhost';"
* mvn clean install
* mvn install package
* java -jar target/restful-user-authentication-0.0.1-SNAPSHOT.jar
## Step -5:
* Visit the url http://localhost:3080/ (or) http://hostname:3080 to access the product
>>>>>>> eb8454f9f5f5b710a49868043b2805f708cb6f48
