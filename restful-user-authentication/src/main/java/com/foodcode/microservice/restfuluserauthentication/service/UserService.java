package com.foodcode.microservice.restfuluserauthentication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodcode.microservice.restfuluserauthentication.controller.UserJPAResource;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.Role;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.RoleRepository;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.UserRepository;

@Service
public class UserService {
	private static final Logger log = 
			LoggerFactory.getLogger(UserJPAResource.class);
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private AuthenticationManager authenticationManager;

	@Autowired
	public UserService(UserRepository userRepository,
			RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.authenticationManager=authenticationManager;
	}

	public User findUserByEmail(String email) {
		System.out.println("Trying here");
		return userRepository.findByEmail(email);
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}  
	public boolean getPasswordMatchStatus(String inputPassoword,User user) {
		return bCryptPasswordEncoder.matches(inputPassoword, user.getPassword());
	}
//	public String getEncodedPassrowd(User user) {
//		return bCryptPasswordEncoder.encode(user.getPassword());
//
//	}
	public String getEncodedString(String password) {
		return bCryptPasswordEncoder.encode(password);

	}
	
	public void setSpringSecurity(User user, String password) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(), password,authorities);
		Authentication auth = authenticationManager.authenticate(authReq);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
		
		log.info("authority is:"+authReq.getAuthorities()+" also if it is authenticate:"+auth.isAuthenticated() );
	}

	
}
