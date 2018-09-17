package com.foodcode.microservice.restfuluserauthentication.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@JsonFilter("UserFilter")
//@JsonIgnoreProperties(value= {"password"})//static filtering
@ApiModel(description="All details about the user")
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=1,message="firstName should have atleast one characters")
	private String firstName;
	
	@Size(min=1,message="lastName should have atleast one characters")
	private String lastName;
	
	@Past
	private Date birthDate;
	
	@Size(min=1,message="selfDescription should have atleast two characters")
	private String selfDescription;
	
	@OneToMany(mappedBy="user")
	private List<Posts> recipeId;
	
	//@Size(min=1,message="Name should have atleast two characters")
	private String emailId;
	
	@Size(min=5,message="userName should have atleast five characters")
	private String userName;
	
//	@JsonIgnore
	private String password;

	public User() {
		super();
	}
	
	
	
	public User(Integer id, @Size(min = 1, message = "firstName should have atleast one characters") String firstName,
			@Size(min = 1, message = "lastName should have atleast one characters") String lastName,
			@Past Date birthDate,
			@Size(min = 1, message = "selfDescription should have atleast two characters") String selfDescription, String emailId,
			@Size(min = 5, message = "userName should have atleast five characters") String userName, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		if(selfDescription == null || selfDescription == "") {
			this.selfDescription = "Something something";
		}
		else {
			this.selfDescription = selfDescription;
		}
		this.emailId = emailId;
		this.userName = userName;
		if(password==null || password =="") {
			this.password = "password";
		}
		else {
			this.password = password;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", selfDescription=" + selfDescription + ", emailId=" + emailId
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

	public List<Posts> getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(List<Posts> recipeId) {
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
