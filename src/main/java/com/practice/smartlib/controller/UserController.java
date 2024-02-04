package com.practice.smartlib.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.practice.smartlib.model.User;
import com.practice.smartlib.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder uriBuilder) {
		User createdUser = service.create(user);
		
		URI uri = uriBuilder.path("user/{id}").buildAndExpand(createdUser.getId()).toUri();
		
		return ResponseEntity.created(uri).body(createdUser);
	}
	
}
