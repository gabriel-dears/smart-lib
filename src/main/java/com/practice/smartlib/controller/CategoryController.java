package com.practice.smartlib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.smartlib.model.Category;
import com.practice.smartlib.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping
	public List<Category> findAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Category findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Category insert(@RequestBody Category category) {
		return service.insertCategory(category);
	}
	
}
