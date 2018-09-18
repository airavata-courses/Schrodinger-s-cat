package com.foodcode.microservice.restfuluserauthentication.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeIdExistsException extends RuntimeException {

	public RecipeIdExistsException(String message) {
		super(message);
	}
	
}