package com.demo.elasticsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.elasticsearch.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
