package com.foodcode.microservice.restfuluserauthentication.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class RegistrationDetailsException extends RuntimeException {

	public RegistrationDetailsException(String message) {
		super(message);
	}
	
}