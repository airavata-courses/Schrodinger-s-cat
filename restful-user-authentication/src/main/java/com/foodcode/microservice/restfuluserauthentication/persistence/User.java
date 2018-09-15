package com.foodcode.microservice.restfuluserauthentication.persistence;

import java.util.Date;

public class User {
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthDate;
	
	private String selfDescription;
	
	private Integer recipeId;
	
	private String emailId;
	
	private String userName;
	
	private String password;

	public User() {
		super();
	}

	public User(Integer id, String firstName, String lastName, Date birthDate, String selfDescription, Integer recipeId,
			String emailId, String userName, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.selfDescription = selfDescription;
		this.recipeId = recipeId;
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", selfDescription=" + selfDescription + ", recipeId=" + recipeId + ", emailId=" + emailId
				+ ", userName=" + userName + ", password=" + password + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSelfDescription() {
		return selfDescription;
	}

	public void setSelfDescription(String selfDescription) {
		this.selfDescription = selfDescription;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
