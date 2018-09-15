package com.foodcode.microservice.restfuluserauthentication.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foodcode.microservice.restfuluserauthentication.dao.UserDAOService;
import com.foodcode.microservice.restfuluserauthentication.persistence.User;
//TODO: define proper exception for all the attributes for User. Like retrieveUsers - 404 not found.
@RestController
public class UserResource {
	
	@Autowired
	private UserDAOService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUsers(@PathVariable int id){
		User user = service.findById(id);
		if(user==null) {
			// it is a runtime exception, not an Exception
			throw new UserNotFoundException("id-"+id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.saveUser(user);

		//get the location of created user
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		//return proper response status, where is the user created
		return ResponseEntity.created(location).build();
	}
}
