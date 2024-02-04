package com.practice.smartlib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.smartlib.model.Category;
import com.practice.smartlib.repository.CategoryRepository;
import com.practice.smartlib.utils.NumberUtils;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Category findById(Long id) {
		
		if( NumberUtils.isEmpty(id) ) {
			throw new IllegalArgumentException("The id is required!");
		}
		
		Category category = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		return category;
	}

	public Category insertCategory(Category category) {
		
		if( repository.findByName(category.getName()).isPresent() ) {
			throw new RuntimeException("A category with the informed name already exists!");
		}
		
		return repository.save(category);
	}

	public List<Category> findAll() {
		return repository.findAll();
	}
	
}
