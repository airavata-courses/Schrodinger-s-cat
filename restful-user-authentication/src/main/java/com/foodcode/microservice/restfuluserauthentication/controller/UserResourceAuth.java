package com.foodcode.microservice.restfuluserauthentication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodcode.microservice.restfuluserauthentication.controller.exception.UserNotFoundException;
import com.foodcode.microservice.restfuluserauthentication.controller.exception.UserPasswordWrongException;
import com.foodcode.microservice.restfuluserauthentication.controller.filter.UserFilterAttributes;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.UserAuthenticationDetails;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.PostsRepository;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.UserRepository;
import com.foodcode.microservice.restfuluserauthentication.service.UserService;


@RestController
public class UserResourceAuth {
	private static final Logger log = 
			LoggerFactory.getLogger(UserJPAResource.class);
	
	@Autowired
	private UserFilterAttributes filterAttributes;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private UserService userService;
	
	/*---------------*/
	@GetMapping("/auth/users/get/all-attributes/{id}")
	public MappingJacksonValue retrieveUserAllAttributes(@PathVariable int id ){
		Optional<User> user = userRepository.findById(id);
		if(!(user.isPresent()))  {
			log.info("user not found the id:"+id);
			throw new UserNotFoundException("id-"+id);
		}
		List<User> userList = new ArrayList<>();
		userList.add(user.get());
		MappingJacksonValue allAttributes = filterAttributes.getAllAttributes(userList);
		log.info("retrieved user:"+id+" all users with all of their attributes");
		return allAttributes;
	}
	
	@GetMapping("/auth/users/get/f-l-name/{id}")
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
	
	@GetMapping("/auth/users/get/uId-emailId/{id}")
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
	
	@GetMapping("/auth/users/get/fn-ln-ui-ps/{id}")
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
	
	@GetMapping("/auth/users/get/fn-ln-ds-re/{id}")
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
	@GetMapping("/auth/users/all-attributes/{id}/onlyposts")
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
	//login service
		@PostMapping("/auth/users/logmein")
		public MappingJacksonValue authenticateUser(@Valid @RequestBody UserAuthenticationDetails userAuthenticationDetails,BindingResult bindingResult) {
			log.info("In /auth/users/login to login user");
			//log.info("following details retrived : "+userAuthenticationDetails.getUsername()+" password "+userAuthenticationDetails.getPassword());
			User user = userService.findUserByEmail(userAuthenticationDetails.getUsername());
			if((user == null))  {
				log.info("user not found with the email id"+userAuthenticationDetails.getUsername());
				throw new UserNotFoundException("username-"+userAuthenticationDetails.getUsername());
			}
			if(userService.getPasswordMatchStatus(userAuthenticationDetails.getPassword(), user)) {
				log.info("passwords match");
				userService.setSpringSecurity(user,userAuthenticationDetails.getPassword());
				List<User> userList = new ArrayList<>();
				userList.add(user);
				MappingJacksonValue allAttributes = filterAttributes.getUserNameAndUserID(userList);
				return allAttributes;
			}
			else {
				throw new UserPasswordWrongException("Entered password: "+userAuthenticationDetails.getPassword()+" is wrong!");
			}
		}
		
		@PostMapping("/auth/users/logmeout")
		public void logoutAuthenticatedUser() {
			log.info("In /auth/users/logmeout PostMapping");
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			if(user == null) {
				throw new UserNotFoundException("username not found");
			}
			else {
				log.info("username present here is"+user.getUsername());
				SecurityContextHolder.clearContext();
			}
//			
//			
//			
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			//log.info("following details retrived : "+userAuthenticationDetails.getUsername()+" password "+userAuthenticationDetails.getPassword());
//			User user = userService.findUserByEmail(userAuthenticationDetails.getUsername());
//			if((user == null))  {
//				log.info("user not found with the email id"+userAuthenticationDetails.getUsername());
//				throw new UserNotFoundException("username-"+userAuthenticationDetails.getUsername());
//			}
//			//User user = optionalUser.get();
//			if(userService.getPasswordMatchStatus(userAuthenticationDetails.getPassword(), user)) {
//				log.info("passwords match");
//				userService.setSpringSecurity(user,userAuthenticationDetails.getPassword());
//				List<User> userList = new ArrayList<>();
//				userList.add(user);
//				MappingJacksonValue allAttributes = filterAttributes.getUserNameFirstLastNamePassword(userList);
//				return allAttributes;
//			}
//			else {
//				throw new UserPasswordWrongException("Entered password: "+userAuthenticationDetails.getPassword()+" is wrong!");
//			}
		}
		
		@GetMapping("/auth/users/logmeout")
		public void logoutAuthenticatedUserGet() {
			log.info("In /auth/users/logmeout GetMapping");
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			if(user == null) {
				throw new UserNotFoundException("username not found");
			}
			else {
				log.info("username present here is"+user.getUsername());
				SecurityContextHolder.clearContext();
			}
		}
}
