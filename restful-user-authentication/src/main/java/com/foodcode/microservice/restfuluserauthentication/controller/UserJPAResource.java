package com.foodcode.microservice.restfuluserauthentication.controller;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.Posts;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.UserAuthenticationDetails;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.PostsRepository;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.UserRepository;
import com.foodcode.microservice.restfuluserauthentication.service.EmitLogDirect;
import com.foodcode.microservice.restfuluserauthentication.service.UserService;
import com.foodcode.microservice.restfuluserauthentication.controller.exception.RecipeIdExistsException;
import com.foodcode.microservice.restfuluserauthentication.controller.exception.RegistrationDetailsException;
import com.foodcode.microservice.restfuluserauthentication.controller.exception.UserNotFoundException;
import com.foodcode.microservice.restfuluserauthentication.controller.exception.UserPasswordWrongException;
import com.foodcode.microservice.restfuluserauthentication.controller.filter.UserFilterAttributes;
import com.foodcode.microservice.restfuluserauthentication.dao.depricated.UserDAOService;
//TODO: define proper exception for all the attributes for User. Like retrieveUsers - 404 not found.
//applying dynamic filtering on attributes being retrieved 
//TODO : add multiple filtering links to retrieve only required fields
@RestController
public class UserJPAResource {

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
	
	@Autowired
	private EmitLogDirect emitLogDirect;

	/*---------------*/
	@GetMapping("/jpa/users/get/all-attributes")
	public MappingJacksonValue retrieveAllUsers(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getAllAttributes(findAll);
		log.info("retrieved all users with all of their attributes");
		return allAttributes;
	}

	@GetMapping("/jpa/users/get/f-l-name")
	public MappingJacksonValue retrieveAllUsersFirstLastName(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getAllFirstLastName(findAll);
		log.info("retrieved all users with first name and last names");
		return allAttributes;
	}

	@GetMapping("/jpa/users/get/uId-emailId")
	public MappingJacksonValue retrieveAllUsersUIdEmailId(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getUserNameEmailId(findAll);
		log.info("retrieved all user ids with email Id");
		return allAttributes;
	}

	@GetMapping("/jpa/users/get/fn-ln-ui-ps")
	public MappingJacksonValue retrieveAllUsersFnLnUnPass(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getUserNameFirstLastNamePassword(findAll);
		log.info("retrieved all users with first name, last names, username and password");
		return allAttributes;
	}

	@GetMapping("/jpa/users/get/fn-ln-ds-re")
	public MappingJacksonValue retrieveAllUsersFnLnDrRId(){
		List<User> findAll = userRepository.findAll();
		MappingJacksonValue allAttributes = filterAttributes.getFirstLastDescriptionRecipeId(findAll);
		log.info("retrieved all users with first name, last names, description and RecipeIds");
		return allAttributes;
	}
	
	@PostMapping("/jpa/users/register")
	public MappingJacksonValue createNewUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		log.info("user email:"+user.getEmail()+" user.getFirstName():"+user.getFirstName()+" user.getLastName():"+user.getLastName()+" user.getSelfDescription():"+user.getSelfDescription()+" user.getUsername():"+user.getUsername()+" user.getRecipeId():"+user.getRecipeId());
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
			.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			throw new RegistrationDetailsException("There is already a user registered with the email "+userExists.getEmail()+" provided");
		} else {
			log.info("user found with the data+ "+user.getEmail());
			userService.saveUser(user);
			List<User> userList = new ArrayList<>();
			userList.add(user);
			MappingJacksonValue allAttributes = filterAttributes.getUserNameAndUserID(userList);
			emitLogDirect.sendEmailToUser(user.getEmail());
			return allAttributes;
		}
	}

}
