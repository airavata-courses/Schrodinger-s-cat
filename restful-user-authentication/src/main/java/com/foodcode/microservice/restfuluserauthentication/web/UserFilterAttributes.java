package com.foodcode.microservice.restfuluserauthentication.web;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.foodcode.microservice.restfuluserauthentication.persistence.User;

@Component
public class UserFilterAttributes {
	
	// To return All the attributes without Password
	public MappingJacksonValue getAllAttributes(List<User> findAll) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("firstName","lastName",
						"birthDate","selfDescription","recipeId",
						"emailId","userName");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(findAll);
		mapping.setFilters(filters);
		return mapping;
	}
	
	//To retrieve only First Name and Last Name
	public MappingJacksonValue getAllFirstLastName(List<User> findAll) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("firstName","lastName");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(findAll);
		mapping.setFilters(filters);
		return mapping;
	}
	
	//To get only user name and Email ID
	public MappingJacksonValue getUserNameEmailId(List<User> findAll) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("emailId","userName");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(findAll);
		mapping.setFilters(filters);
		return mapping;
	}
	
	//To get first name, last name, user name and password 
	public MappingJacksonValue getUserNameFirstLastNamePassword(List<User> findAll) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("firstName","lastName",
						"userName","password");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(findAll);
		mapping.setFilters(filters);
		return mapping;
	}
	
	//To first name, last name, description, recipeIds
	public MappingJacksonValue getFirstLastDescriptionRecipeId(List<User> findAll) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("firstName","lastName",
						"selfDescription","recipeId");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(findAll);
		mapping.setFilters(filters);
		return mapping;
	}
	
}
