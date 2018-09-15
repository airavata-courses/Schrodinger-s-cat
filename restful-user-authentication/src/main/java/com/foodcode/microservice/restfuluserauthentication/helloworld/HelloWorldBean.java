package com.foodcode.microservice.restfuluserauthentication.helloworld;

public class HelloWorldBean{
	private String message;

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	public HelloWorldBean() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
	
	
}
