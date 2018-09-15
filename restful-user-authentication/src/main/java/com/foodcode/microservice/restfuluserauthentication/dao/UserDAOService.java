package com.foodcode.microservice.restfuluserauthentication.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.foodcode.microservice.restfuluserauthentication.persistence.User;

@Component
public class UserDAOService implements UserDAO {
	
	//
	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	static {
		users.add(new User(1,"Adam","Baccha",new Date(),"Mein bada chef hoon",
				0,"adamBaccha@example.com","adam_baccha","password"));
		users.add(new User(2,"Zebra","Baccha",new Date(),"Mein bada nahi chef hoon",
				0,"zebraBaccha@example.com","zebra_baccha","password"));
		users.add(new User(3,"Horse","Baccha",new Date(),"Mein bada chef hoon re pakka",
				0,"horseBaccha@example.com","horse_baccha","password"));
	}
	//
	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User findById(Integer id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User saveUser(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
		
	}

	@Override
	public User deleteUserById(Integer id) {
		//using iterator to iterate over the users list
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	} 
}
