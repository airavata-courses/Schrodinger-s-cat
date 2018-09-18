package com.foodcode.microservice.restfuluserauthentication.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JDBCConnectionFailed extends RuntimeException {

	public JDBCConnectionFailed(String message) {
		super(message);
	}
	
}