package com.foodcode.microservice.restfuluserauthentication.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foodcode.microservice.restfuluserauthentication.controller.exception.RecipeIdExistsException;
import com.foodcode.microservice.restfuluserauthentication.controller.exception.RegistrationDetailsException;
import com.foodcode.microservice.restfuluserauthentication.controller.exception.UserNotFoundException;
import com.foodcode.microservice.restfuluserauthentication.controller.exception.UserPasswordWrongException;
import com.foodcode.microservice.restfuluserauthentication.controller.filter.UserFilterAttributes;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.Posts;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.UserAuthenticationDetails;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.PostsRepository;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.UserRepository;
import com.foodcode.microservice.restfuluserauthentication.service.EmitLogDirect;
import com.foodcode.microservice.restfuluserauthentication.service.UserService;

@RestController
public class UserResourceEdit {
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

//	@PostMapping("/jpa/users/create")
//	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
//		log.info("In /jpa/users/create to create post");
//		User savedUser = userRepository.save(user);
//		log.info("retrieved user");
//		//get the location of created user
//		URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedUser.getId()).toUri();
//
//		//return proper response status, where is the user created
//		log.info("created User: "+savedUser);
//		return ResponseEntity.created(location).build();
//	}

	@PostMapping("/jpa/users/create/{id}/save-posts")
	public ResponseEntity<Object> createUserPosts(@PathVariable int id, @RequestBody Posts post) {
		log.info("In /jpa/users/create/{id}/save-posts to create post");
		Optional<User> savedUser = userRepository.findById(id);
		log.info("retrieved user");
		if(!(savedUser.isPresent()))  {
			log.info("user not present");
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

//	@DeleteMapping("/jpa/users/delete/{id}")
//	public void deleteUser(@PathVariable int id) {
//		Optional<User> savedUser = userRepository.findById(id);
//		if(!(savedUser.isPresent()))  {
//			log.info("exception at deleting user with the id:"+id);
//			throw new UserNotFoundException("id-"+id);
//		}
//		User user = savedUser.get();
//		boolean containsFlag = false;
//		if(!(user.getRecipeId().isEmpty())) {
//			for(Posts delPost:user.getRecipeId()) {
//				postsRepository.deleteById(delPost.getId());
//			}
//		}
//		userRepository.deleteById(id);
//		log.info("Deleted user with the id:"+id);
//	}

	@DeleteMapping("/jpa/users/delete/{id}/delete-post")
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
