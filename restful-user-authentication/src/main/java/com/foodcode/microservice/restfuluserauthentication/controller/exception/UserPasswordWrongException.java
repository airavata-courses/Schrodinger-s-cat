package com.foodcode.microservice.restfuluserauthentication.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UserPasswordWrongException extends RuntimeException {

	public UserPasswordWrongException(String message) {
		super(message);
	}
	
}