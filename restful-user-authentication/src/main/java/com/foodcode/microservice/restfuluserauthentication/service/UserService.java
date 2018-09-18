package com.foodcode.microservice.restfuluserauthentication.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodcode.microservice.restfuluserauthentication.persistence.model.Role;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.RoleRepository;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
	
	 public User saveUser(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setActive(1);
	        Role userRole = roleRepository.findByRole("ADMIN");
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	        return userRepository.save(user);
	 }  
}
