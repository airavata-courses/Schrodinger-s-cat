# Schrodinger-s-cat

Wiki Link : https://github.com/airavata-courses/Schrodinger-s-cat/wiki

## foodcode
An interactive website where you can browse for recipes, upload your own creations and also check-out what your friends are cooking! Get upvotes, suggestions and recommendations. Just Sign up!

### About this branch
* Master Branch.
* Stable Releases
* Team members: [Nawaz](https://www.linkedin.com/in/nawazhk/), [Prashanth](https://www.linkedin.com/in/prashanth-swargam-pswargam/) and [Soumya](https://www.linkedin.com/in/jlsoumya/)

#### What all added? 
- Swagger UI
- Customized Exception hanlding
- Dynamic Filtering 
- API Modeling

### Links
#### Post
- http://localhost:8888/jpa/users							- createUser
- http://localhost:8888/jpa/users/{id}/save-posts				- createUserPosts

#### Get
- http://localhost:8888/jpa/users/all-attributes 				- retrieveAllUsers
- http://localhost:8888/jpa/users/f-l-name 					- retrieveAllUsersFirstLastName
- http://localhost:8888/jpa/users/uId-emailId 				- retrieveAllUsersUIdEmailId
- http://localhost:8888/jpa/users/fn-ln-ui-ps				- retrieveAllUsersFnLnUnPass
- http://localhost:8888/jpa/users/fn-ln-ds-re				- retrieveAllUsersFnLnDrRId

- http://localhost:8888/jpa/users/all-attributes/{id}			- retrieveUserAllAttributes
- http://localhost:8888/jpa/users/f-l-name/{id}				- retrieveUserFirstLastName
- http://localhost:8888/jpa/users/uId-emailId/{id}			- retrieveUserIdEmail
- http://localhost:8888/jpa/users/fn-ln-ui-ps/{id}			- retrieveFirstLastUserIdPass
- http://localhost:8888/jpa/users/fn-ln-ds-re/{id}			- retrieveFirstLastDescRecipeId

#### Delete
- http://localhost:8888/jpa/users/{id}						- deleteUser
- http://localhost:8888/jpa/users/{id}/delete-post			- deleteUserPost