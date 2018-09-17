package com.foodcode.microservice.restfuluserauthentication.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.foodcode.microservice.restfuluserauthentication.dao.UserDAOService;
import com.foodcode.microservice.restfuluserauthentication.persistence.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.UserRepository;
import com.foodcode.microservice.restfuluserauthentication.persistence.Posts;
import com.foodcode.microservice.restfuluserauthentication.persistence.PostsRepository;
//TODO: define proper exception for all the attributes for User. Like retrieveUsers - 404 not found.
//applying dynamic filtering on attributes being retrieved 
//TODO : add multiple filtering links to retrieve only required fields
@RestController
public class UserJPAResource {

	private static final Logger log = 
			LoggerFactory.getLogger(UserJPAResource.class);
	
	@Autowired
	private UserFilterAttributes filterAttributes;

//	@Autowired
//	private UserDAOService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostsRepository postsRepository;

	/*---------------*/
	@GetMapping("/jpa/users/all-attributes")
	public MappingJacksonValue retrieveAllUsers(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getAllAttributes(findAll);
		log.info("retrieved all users with all of their attributes");
		return allAttributes;
	}

	@GetMapping("/jpa/users/f-l-name")
	public MappingJacksonValue retrieveAllUsersFirstLastName(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getAllFirstLastName(findAll);
		log.info("retrieved all users with first name and last names");
		return allAttributes;
	}

	@GetMapping("/jpa/users/uId-emailId")
	public MappingJacksonValue retrieveAllUsersUIdEmailId(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getUserNameEmailId(findAll);
		log.info("retrieved all user ids with email Id");
		return allAttributes;
	}

	@GetMapping("/jpa/users/fn-ln-ui-ps")
	public MappingJacksonValue retrieveAllUsersFnLnUnPass(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getUserNameFirstLastNamePassword(findAll);
		log.info("retrieved all users with first name, last names, username and password");
		return allAttributes;
	}

	@GetMapping("/jpa/users/fn-ln-ds-re")
	public MappingJacksonValue retrieveAllUsersFnLnDrRId(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getFirstLastDescriptionRecipeId(findAll);
		log.info("retrieved all users with first name, last names, description and RecipeIds");
		return allAttributes;
	}
	/*---------------*/

	/*---------------*/
	@GetMapping("/jpa/users/all-attributes/{id}")
	public MappingJacksonValue retrieveUserAllAttributes(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!(user.isPresent()))  {
			log.info("exception at deleting user with the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user.get());

		//HATEOAS: to get all the other links for getting all users
		//		Resource<User> resource = new Resource<User>(user);
		//		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		//		resource.add(linkTo.withRel("all-users"));


		MappingJacksonValue allAttributes = filterAttributes.getAllAttributes(userList);
		log.info("retrieved user:"+id+" all users with all of their attributes");
		return allAttributes;
	}
	
	@GetMapping("/jpa/users/f-l-name/{id}")
	public MappingJacksonValue retrieveUserFirstLastName(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!(user.isPresent()))  {
			log.info("exception at deleting user with the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user.get());
		MappingJacksonValue allAttributes = filterAttributes.getAllFirstLastName(userList);
		log.info("retrieved user:"+id+" users with first name and last names");
		return allAttributes;
	}
	
	@GetMapping("/jpa/users/uId-emailId/{id}")
	public MappingJacksonValue retrieveUserIdEmail(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!(user.isPresent()))  {
			log.info("exception at deleting user with the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user.get());
		MappingJacksonValue allAttributes = filterAttributes.getUserNameEmailId(userList);
		log.info("retrieved user:"+id+" ids with email Id");
		return allAttributes;
	}
	
	@GetMapping("/jpa/users/fn-ln-ui-ps/{id}")
	public MappingJacksonValue retrieveFirstLastUserIdPass(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!(user.isPresent()))  {
			log.info("exception at deleting user with the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user.get());
		MappingJacksonValue allAttributes = filterAttributes.getUserNameFirstLastNamePassword(userList);
		log.info("retrieved user:"+id+" with first name, last names, username and password");
		return allAttributes;
	}
	
	@GetMapping("/jpa/users/fn-ln-ds-re/{id}")
	public MappingJacksonValue retrieveFirstLastDescRecipeId(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!(user.isPresent()))  {
			log.info("exception at deleting user with the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user.get());
		MappingJacksonValue allAttributes = filterAttributes.getFirstLastDescriptionRecipeId(userList);
		log.info("retrieved user:"+id+" with first name, last names, description and RecipeIds");
		return allAttributes;
	}
	@GetMapping("/jpa/users/all-attributes/{id}/onlyposts")
	public MappingJacksonValue retrieveAllUsersAndPosts(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!(user.isPresent()))  {
			log.info("exception at deleting user with the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user.get());
		MappingJacksonValue allAttributes = filterAttributes.getUserIdRecipeId(userList);
		log.info("retrieved user:"+id+" with RecipeIds");
		return allAttributes;
	}
	
	/*---------------*/	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		//get the location of created user
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		//return proper response status, where is the user created
		log.info("created User: "+savedUser);
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/jpa/users/{id}/save-posts")
	public ResponseEntity<Object> createUserPosts(@PathVariable int id, @RequestBody Posts post) {
		Optional<User> savedUser = userRepository.findById(id);
		if(!(savedUser.isPresent()))  {
			throw new UserNotFoundException("id-"+id);
		}
		
		User user = savedUser.get();
		if(!(user.getRecipeId().isEmpty())){
			for(Posts recipeIds:user.getRecipeId()) {
				if(recipeIds.getId() == post.getId() ) {
					log.info("exception at adding user's recipe with the id:"+post.getId());
					throw new RecipeIdExistsException("Recipe id-"+post.getId()+" already exists add another recipe");
				}
			}
		}
		
		post.setUser(user);
		post.setId(post.getId());
		postsRepository.save(post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.get().getId()).toUri();
		log.info("created User's: "+savedUser+" Post with the Id: "+post.getId());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!(user.isPresent()))  {
			log.info("exception at deleting user with the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		userRepository.deleteById(id);
		log.info("Deleted user with the id:"+id);
	}
	
	@DeleteMapping("/jpa/users/{id}/delete-post")
	public void deleteUserPost(@PathVariable int id, @RequestBody Posts post) {
		Optional<User> savedUser = userRepository.findById(id);
		if(!(savedUser.isPresent()))  {
			log.info("exception at deleting user with the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		
		User user = savedUser.get();
		boolean containsFlag = false;
		for(Posts delPost:user.getRecipeId()) {
			if(delPost.getId()==post.getId()) {
				containsFlag = true;
				postsRepository.deleteById(post.getId());
			}
		}
		if(!containsFlag) {
			log.info("exception at deleting user's recipe with the id:"+post.getId());
			throw new RecipeIdExistsException("Recipe id-"+post.getId()+" does not exit, cannot delete it");
		}
		log.info("Deleted user's:"+id+" recipe with the id:"+post.getId());
	}
}
