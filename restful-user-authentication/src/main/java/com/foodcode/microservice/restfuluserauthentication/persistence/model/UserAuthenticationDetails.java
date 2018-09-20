package com.foodcode.microservice.restfuluserauthentication.persistence.model;

import io.swagger.annotations.ApiModel;

@ApiModel(description="contains username and passsword")
public class UserAuthenticationDetails {
	private String username;
	private String password;
	public UserAuthenticationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAuthenticationDetails(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserAuthenticationDetails [username=" + username + ", password=" + password + "]";
	}
	
}
