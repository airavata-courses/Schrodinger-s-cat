package com.foodcode.microservice.restfuluserauthentication.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodcode.microservice.restfuluserauthentication.persistence.model.Posts;

public interface PostsRepository extends JpaRepository<Posts,Integer> {

}

