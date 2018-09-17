package com.foodcode.microservice.restfuluserauthentication.web.depricated;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.foodcode.microservice.restfuluserauthentication.dao.depricated.UserDAOService;
import com.foodcode.microservice.restfuluserauthentication.persistence.User;
import com.foodcode.microservice.restfuluserauthentication.web.UserFilterAttributes;
import com.foodcode.microservice.restfuluserauthentication.web.UserNotFoundException;
//TODO: define proper exception for all the attributes for User. Like retrieveUsers - 404 not found.
//applying dynamic filtering on attributes being retrieved 
//TODO : add multiple filtering links to retrieve only required fields
@RestController
public class UserResource {

	@Autowired
	private UserFilterAttributes filterAttributes;

	@Autowired
	private UserDAOService service;

	/*---------------*/
	@GetMapping("/users/all-attributes")
	public MappingJacksonValue retrieveAllUsers(){
		List<User> findAll = service.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getAllAttributes(findAll);
		return allAttributes;
	}

	@GetMapping("/users/f-l-name")
	public MappingJacksonValue retrieveAllUsersFirstLastName(){
		List<User> findAll = service.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getAllFirstLastName(findAll);
		return allAttributes;
	}

	@GetMapping("/users/uId-emailId")
	public MappingJacksonValue retrieveAllUsersUIdEmailId(){
		List<User> findAll = service.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getUserNameEmailId(findAll);
		return allAttributes;
	}

	@GetMapping("/users/fn-ln-ui-ps")
	public MappingJacksonValue retrieveAllUsersFnLnUnPass(){
		List<User> findAll = service.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getUserNameFirstLastNamePassword(findAll);
		return allAttributes;
	}

	@GetMapping("/users/fn-ln-ds-re")
	public MappingJacksonValue retrieveAllUsersFnLnDrRId(){
		List<User> findAll = service.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getFirstLastDescriptionRecipeId(findAll);
		return allAttributes;
	}
	/*---------------*/

	/*---------------*/
	@GetMapping("/users/all-attributes/{id}")
	public MappingJacksonValue retrieveUserAllAttributes(@PathVariable int id){
		User user = service.findById(id);
		if(user==null)  {
			// it is a runtime exception, not an Exception
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user);

		//HATEOAS: to get all the other links for getting all users
		//		Resource<User> resource = new Resource<User>(user);
		//		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		//		resource.add(linkTo.withRel("all-users"));


		MappingJacksonValue allAttributes = filterAttributes.getAllAttributes(userList);
		return allAttributes;
	}
	
	@GetMapping("/users/f-l-name/{id}")
	public MappingJacksonValue retrieveUserFirstLastName(@PathVariable int id){
		User user = service.findById(id);
		if(user==null)  {
			// it is a runtime exception, not an Exception
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user);
		MappingJacksonValue allAttributes = filterAttributes.getAllFirstLastName(userList);
		return allAttributes;
	}
	
	@GetMapping("/users/uId-emailId/{id}")
	public MappingJacksonValue retrieveUserIdEmail(@PathVariable int id){
		User user = service.findById(id);
		if(user==null)  {
			// it is a runtime exception, not an Exception
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user);
		MappingJacksonValue allAttributes = filterAttributes.getUserNameEmailId(userList);
		return allAttributes;
	}
	
	@GetMapping("/users/fn-ln-ui-ps/{id}")
	public MappingJacksonValue retrieveFirstLastUserIdPass(@PathVariable int id){
		User user = service.findById(id);
		if(user==null)  {
			// it is a runtime exception, not an Exception
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user);
		MappingJacksonValue allAttributes = filterAttributes.getUserNameFirstLastNamePassword(userList);
		return allAttributes;
	}
	
	@GetMapping("/users/fn-ln-ds-re/{id}")
	public MappingJacksonValue retrieveFirstLastDescRecipeId(@PathVariable int id){
		User user = service.findById(id);
		if(user==null)  {
			// it is a runtime exception, not an Exception
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user);
		MappingJacksonValue allAttributes = filterAttributes.getFirstLastDescriptionRecipeId(userList);
		return allAttributes;
	}
	
	
	/*---------------*/

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.saveUser(user);

		//get the location of created user
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		//return proper response status, where is the user created
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteUserById(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
	}
}
