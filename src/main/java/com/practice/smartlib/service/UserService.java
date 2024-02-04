package com.practice.smartlib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.smartlib.model.User;
import com.practice.smartlib.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User create(User user) {
		return repository.save(user);
	}

}
