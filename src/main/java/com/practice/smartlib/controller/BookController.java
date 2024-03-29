package com.practice.smartlib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.practice.smartlib.dto.BookDto;
import com.practice.smartlib.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping
	public ResponseEntity<List<BookDto>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<BookDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findByIdDto(id));
	}
	
	@PostMapping
	public ResponseEntity<BookDto> insert(@Valid @RequestBody BookDto dto, UriComponentsBuilder uribBuilder) {
		var createdDto = service.create(dto);
		var path = uribBuilder.path("/book/{id}").buildAndExpand(createdDto.getId()).toUri();
		return ResponseEntity.created(path).body(createdDto);
	}
	
	// TODO: put
	// same structure as borrowing... create toDto() method
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
