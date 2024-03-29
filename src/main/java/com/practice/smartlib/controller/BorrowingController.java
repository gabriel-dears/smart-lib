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

import com.practice.smartlib.dto.BorrowingDto;
import com.practice.smartlib.service.BorrowingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("borrowing")
public class BorrowingController {

	@Autowired
	private BorrowingService service;
	
	@GetMapping
	public ResponseEntity<List<BorrowingDto>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<BorrowingDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<BorrowingDto> create(@Valid @RequestBody BorrowingDto borrowing, UriComponentsBuilder uriBuilder) {
		
		BorrowingDto createdBorrowing = service.create(borrowing);
		
		URI path = uriBuilder.path("/borrowing/{id}").buildAndExpand(createdBorrowing.getId()).toUri();
		
		return ResponseEntity.created(path).body(createdBorrowing);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<BorrowingDto> update(@PathVariable Long id, @Valid @RequestBody BorrowingDto dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
