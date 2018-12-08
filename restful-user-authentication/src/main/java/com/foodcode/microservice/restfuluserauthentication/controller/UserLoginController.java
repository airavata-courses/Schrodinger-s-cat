package com.foodcode.microservice.restfuluserauthentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foodcode.microservice.restfuluserauthentication.controller.exception.UserNotFoundException;
import com.foodcode.microservice.restfuluserauthentication.controller.filter.UserFilterAttributes;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.PostsRepository;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.UserRepository;
import com.foodcode.microservice.restfuluserauthentication.service.UserService;

public class UserLoginController {

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

	//login service
	//	@PostMapping("/auth/users/logmein")
	//	public MappingJacksonValue authenticateUser(@Valid @RequestBody UserAuthenticationDetails userAuthenticationDetails,BindingResult bindingResult) {
	//		log.info("In /auth/users/login to login user");
	//		//log.info("following details retrived : "+userAuthenticationDetails.getUsername()+" password "+userAuthenticationDetails.getPassword());
	//		User user = userService.findUserByEmail(userAuthenticationDetails.getUsername());
	//		if((user == null))  {
	//			log.info("user not found with the email id"+userAuthenticationDetails.getUsername());
	//			throw new UserNotFoundException("username-"+userAuthenticationDetails.getUsername());
	//		}
	//		if(userService.getPasswordMatchStatus(userAuthenticationDetails.getPassword(), user)) {
	//			log.info("passwords match");
	//			userService.setSpringSecurity(user,userAuthenticationDetails.getPassword());
	//			List<User> userList = new ArrayList<>();
	//			userList.add(user);
	//			MappingJacksonValue allAttributes = filterAttributes.getUserNameAndUserID(userList);
	//			return allAttributes;
	//		}
	//		else {
	//			throw new UserPasswordWrongException("Entered password: "+userAuthenticationDetails.getPassword()+" is wrong!");
	//		}
	//	}

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
		//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//		//log.info("following details retrived : "+userAuthenticationDetails.getUsername()+" password "+userAuthenticationDetails.getPassword());
		//		User user = userService.findUserByEmail(userAuthenticationDetails.getUsername());
		//		if((user == null))  {
		//			log.info("user not found with the email id"+userAuthenticationDetails.getUsername());
		//			throw new UserNotFoundException("username-"+userAuthenticationDetails.getUsername());
		//		}
		//		//User user = optionalUser.get();
		//		if(userService.getPasswordMatchStatus(userAuthenticationDetails.getPassword(), user)) {
		//			log.info("passwords match");
		//			userService.setSpringSecurity(user,userAuthenticationDetails.getPassword());
		//			List<User> userList = new ArrayList<>();
		//			userList.add(user);
		//			MappingJacksonValue allAttributes = filterAttributes.getUserNameFirstLastNamePassword(userList);
		//			return allAttributes;
		//		}
		//		else {
		//			throw new UserPasswordWrongException("Entered password: "+userAuthenticationDetails.getPassword()+" is wrong!");
		//		}
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
	
	@GetMapping("/error")
	public ModelAndView LoginUser(BindingResult bindingResult) {
		log.info("/error");
		
		ModelAndView modelAndView = new ModelAndView();
//		User user = new User();
//		modelAndView.addObject("user", user);

		bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		modelAndView.setViewName("/auth/users/logmein");
		return modelAndView;
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = userService.findUserByEmail(auth.getName());
//		if(user == null) {
//			throw new UserNotFoundException("username not found");
//		}
//		else {
//			log.info("username present here is"+user.getUsername());
//			SecurityContextHolder.clearContext();
//		}
	}
}
