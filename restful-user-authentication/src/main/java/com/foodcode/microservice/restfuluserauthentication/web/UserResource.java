package com.foodcode.microservice.restfuluserauthentication.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.foodcode.microservice.restfuluserauthentication.dao.UserDAOService;
import com.foodcode.microservice.restfuluserauthentication.persistence.User;

@RestController
public class UserResource {
	
	@Autowired
	private UserDAOService service;
	
	@GetMapping("/users")
	public List<User> retrieveALlUsers(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveALlUsers(@PathVariable int id){
		return service.findById(id);
	}
	
	@PostMapping("/users")
	public void createUser() {
		
	}
}
