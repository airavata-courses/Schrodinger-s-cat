package com.foodcode.microservice.restfuluserauthentication.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	User findByEmail(String email);
}
