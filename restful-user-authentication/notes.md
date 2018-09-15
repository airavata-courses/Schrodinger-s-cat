# RESTful Web Services

Food Recipe Recommendation service

User -> Posts

- Retrieve all Users		- GET /users
- Create a User			- POST /users
- Retrieve one Users		- GET /users/{id} -> /users/1
- Delete a User			- DELETE /users/{id} -> /users/1

- Retrieve all posts for a User 	- GET /users/{id}/posts
- Create a posts for a User 		- POST /users/{id}/posts
- Retrieve details of a post		- GET /users/{id}/posts/{post_id}