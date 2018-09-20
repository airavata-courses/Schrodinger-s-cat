package com.foodcode.microservice.restfuluserauthentication.controller.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//apply to all controllers/resource
@ControllerAdvice// share things against multiple resources
@RestController
public class FoodCodeReponseEntityExceptionHandler 
extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException
	(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse=
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({UserNotFoundException.class, RecipeIdExistsException.class,UsernameNotFoundException.class})
	public final ResponseEntity<Object> handleUserException
	(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse=
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({JDBCConnectionFailed.class})
	public final ResponseEntity<Object> handleUserException1
	(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse=
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,
				HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ExceptionHandler({UserPasswordWrongException.class})
	public final ResponseEntity<Object> handleUserException2
	(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse=
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,
				HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({RegistrationDetailsException.class})
	public final ResponseEntity<Object> handleUserException3
	(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse=
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,
				HttpStatus.IM_USED);
	}
	
	
	@Override
	public final ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse=
				new ExceptionResponse(new Date(),"Validation Failed",
						ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse,
				HttpStatus.BAD_REQUEST);
	}
	
		
}
