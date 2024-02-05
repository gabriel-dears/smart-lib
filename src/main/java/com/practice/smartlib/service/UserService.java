package com.practice.smartlib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.smartlib.model.User;
import com.practice.smartlib.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User create(User user) {
		return repository.save(user);
	}

	public User findById(Long userId) {
		return repository.findById(userId).orElseThrow(() -> new EntityNotFoundException());
	}

	public List<User> findAll() {
		return repository.findAll();
	}

}
