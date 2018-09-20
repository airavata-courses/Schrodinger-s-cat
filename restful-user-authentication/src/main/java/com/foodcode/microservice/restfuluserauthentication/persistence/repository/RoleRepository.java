package com.foodcode.microservice.restfuluserauthentication.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodcode.microservice.restfuluserauthentication.persistence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	  Role findByRole(String role);
}
