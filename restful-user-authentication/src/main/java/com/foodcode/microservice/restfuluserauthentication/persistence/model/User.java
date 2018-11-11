package com.foodcode.microservice.restfuluserauthentication.persistence.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@JsonFilter("UserFilter")
@ApiModel(description="All details about the user")
@Entity
@Table(name="myUser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	
	@Size(min=1,message="firstName should have atleast one characters")
	@NotEmpty(message = "*Please provide a firstname")
	private String firstName;
	
	@Size(min=1,message="lastName should have atleast one characters")
	@NotEmpty(message = "*Please provide a lastname")
	private String lastName;
	
//	@Past
//	@NotEmpty(message = "*Please provide a birthdate")
//	private Date birthDate;
	
	@Size(min=5,message="selfDescription should have atleast ten characters")
	@NotEmpty(message = "*Please provide a lastname")
	private String selfDescription;
	
	@OneToMany(mappedBy="user")
	private List<Posts> recipeId;
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Size(min=5,message="username should have atleast five characters")
	private String username;
	
	@Column(name = "password")
	@Size(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
	private String password;
	
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id,
			@Size(min = 1, message = "firstName should have atleast one characters") @NotEmpty(message = "*Please provide a firstname") String firstName,
			@Size(min = 1, message = "lastName should have atleast one characters") @NotEmpty(message = "*Please provide a lastname") String lastName,
			@Size(min = 10, message = "selfDescription should have atleast ten characters") @NotEmpty(message = "*Please provide a lastname") String selfDescription,
			List<Posts> recipeId,
			@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email,
			@Size(min = 5, message = "username should have atleast five characters") String username,
			@Size(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password,
			int active, Set<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.selfDescription = selfDescription;
		this.recipeId = recipeId;
		this.email = email;
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", selfDescription="
				+ selfDescription + ", recipeId=" + recipeId + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", active=" + active + ", roles=" + roles + "]";
	}
	
	
}
