package com.practice.smartlib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.smartlib.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
