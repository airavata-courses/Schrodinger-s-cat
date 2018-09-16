package com.foodcode.microservice.restfuluserauthentication.persistence;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@JsonFilter("UserFilter")
//@JsonIgnoreProperties(value= {"password"})//static filtering
@ApiModel(description="All details about the user")
public class User {
	private Integer id;
	
	@Size(min=1,message="firstName should have atleast one characters")
	private String firstName;
	
	@Size(min=1,message="lastName should have atleast one characters")
	private String lastName;
	
	@Past
	private Date birthDate;
	
	@Size(min=1,message="selfDescription should have atleast two characters")
	private String selfDescription;
	
	private Integer recipeId;
	
	//@Size(min=1,message="Name should have atleast two characters")
	private String emailId;
	
	@Size(min=5,message="userName should have atleast five characters")
	private String userName;
	
//	@JsonIgnore
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
