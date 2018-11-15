# Schrodinger-s-cat  
[![Build Status](https://travis-ci.org/airavata-courses/Schrodinger-s-cat.svg?branch=feature-login_authenticatiion)](https://travis-ci.org/airavata-courses/Schrodinger-s-cat)

[Wiki Link](https://github.com/airavata-courses/Schrodinger-s-cat/wiki)

Build test - Raghottam

## foodcode  
An interactive website where you can browse for recipes, upload your own creations and also check-out what your friends are cooking! Get upvotes, suggestions and recommendations. Just Sign up!

### About this branch
* Feature-Login-Authentication Branch.
* Team members: [Nawaz](https://www.linkedin.com/in/nawazhk/), [Prashanth](https://www.linkedin.com/in/prashanth-swargam-pswargam/) and [Soumya](https://www.linkedin.com/in/jlsoumya/)

### How to run this project?
- You need Postman to test the functionality of this module. Download postman from https://www.getpostman.com.
- You also need to have an installed instance of PostgresSQL and postgresql-contrib in your local machine.
- Then run the following commands:
	- sudo -u postgres createuser -s postgres
- Then Check out/clone this repository.
- Run _mvn clean install_ to generate the targer jar.
- run java -jar /target/restful-user-authentication-0.0.1-SNAPSHOT.jar --spring.config.name=deploy --spring.config.location=classpath:/prodConfigs/
- GoTo What all added

### Old Steps will be removed
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
- http://localhost:8888/jpa/users/create					- createUser
- http://localhost:8888/jpa/users/create/{id}/save-posts			- createUserPosts

#### Get (require no authentication)
- http://localhost:8888/jpa/users/get/all-attributes 				- retrieveAllUsers
- http://localhost:8888/jpa/users/get/f-l-name 					- retrieveAllUsersFirstLastName
- http://localhost:8888/jpa/users/get/uId-emailId 				- retrieveAllUsersUIdEmailId
- http://localhost:8888/jpa/users/get/fn-ln-ui-ps				- retrieveAllUsersFnLnUnPass
- http://localhost:8888/jpa/users/get/fn-ln-ds-re				- retrieveAllUsersFnLnDrRId
- http://localhost:8888/jpa/users/get/all-attributes/{id}			- retrieveUserAllAttributes
- http://localhost:8888/jpa/users/get/f-l-name/{id}				- retrieveUserFirstLastName
- http://localhost:8888/jpa/users/get/uId-emailId/{id}				- retrieveUserIdEmail
- http://localhost:8888/jpa/users/get/fn-ln-ui-ps/{id}				- retrieveFirstLastUserIdPass
- http://localhost:8888/jpa/users/get/fn-ln-ds-re/{id}				- retrieveFirstLastDescRecipeId

#### Delete (requires user authentication)
- http://localhost:8888/jpa/users/delete/{id}					- deleteUser
- http://localhost:8888/jpa/users/delete/{id}/delete-post			- deleteUserPost
