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
####Post
- /jpa/users							- createUser
- /jpa/users/{id}/save-posts			- createUserPosts

####Get
- /jpa/users/all-attributes 			- retrieveAllUsers
- /jpa/users/f-l-name 				- retrieveAllUsersFirstLastName
- /jpa/users/uId-emailId 			- retrieveAllUsersUIdEmailId
- /jpa/users/fn-ln-ui-ps				- retrieveAllUsersFnLnUnPass
- /jpa/users/fn-ln-ds-re				- retrieveAllUsersFnLnDrRId

- /jpa/users/all-attributes/{id}		- retrieveUserAllAttributes
- /jpa/users/f-l-name/{id}			- retrieveUserFirstLastName
- /jpa/users/uId-emailId/{id}		- retrieveUserIdEmail
- /jpa/users/fn-ln-ui-ps/{id}		- retrieveFirstLastUserIdPass
- /jpa/users/fn-ln-ds-re/{id}		- retrieveFirstLastDescRecipeId

####Delete
- /jpa/users/{id}					- deleteUser
- jpa/users/{id}/delete-post			- deleteUserPost