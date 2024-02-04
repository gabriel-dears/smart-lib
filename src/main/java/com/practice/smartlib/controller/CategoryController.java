package com.practice.smartlib.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.practice.smartlib.model.Category;
import com.practice.smartlib.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Category> insert(@Valid @RequestBody Category category, UriComponentsBuilder uriBuilder) {
		Category insertedCategory = service.insertCategory(category);
		URI path = uriBuilder.path("/category/{id}").buildAndExpand(insertedCategory.getId()).toUri();
		return ResponseEntity.created(path).body(insertedCategory);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Category> update(@PathVariable Long id, @Valid @RequestBody Category category) {
		return ResponseEntity.ok(service.update(id, category));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
