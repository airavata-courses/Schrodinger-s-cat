package com.foodcode.microservice.restfuluserauthentication.dao;

import java.util.List;

import com.foodcode.microservice.restfuluserauthentication.persistence.User;

public interface UserDAO {
	List<User> findAll();
	User findById(Integer id);
	User saveUser(User user);
	User deleteUserById(Integer id);
	
	// TODO: implement the below func
	//List<User> findByName();
	
}
