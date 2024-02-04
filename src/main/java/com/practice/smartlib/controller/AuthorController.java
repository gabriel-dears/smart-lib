package com.practice.smartlib.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.practice.smartlib.model.Author;
import com.practice.smartlib.service.AuthorService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("author")
public class AuthorController {

	@Autowired
	private AuthorService service;
	
	@GetMapping
	public ResponseEntity<List<Author>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Author> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findBydId(id));
	}
	
	@PostMapping
	public ResponseEntity<Author> insert(@Valid @RequestBody Author dto, UriComponentsBuilder uriBuilder) {
		Author author = service.insert(dto);
		URI path = uriBuilder.path("/author/{id}").buildAndExpand(author.getId()).toUri();
		return ResponseEntity.created(path).body(author);
	}
	
	// TODO: update
	
}
