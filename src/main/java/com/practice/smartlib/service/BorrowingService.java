package com.practice.smartlib.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.smartlib.dto.BorrowedBookDto;
import com.practice.smartlib.dto.BorrowingDto;
import com.practice.smartlib.model.Borrowing;
import com.practice.smartlib.repository.BorrowingRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BorrowingService {

	@Autowired
	private BorrowingRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private ModelMapper modelMapper;

	public List<BorrowingDto> findAll() {
		return repository.findAll().stream().map(Borrowing::toDto).toList();
	}

	public BorrowingDto findById(Long id) {
		Borrowing borrowing = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

		return borrowing.toDto();
	}

	public BorrowingDto create(BorrowingDto dto) {
		validateBooksExists(dto.getBorrowedBooks());
		return modelMapper.map(repository.save(dto.toEntity(dto, userService, bookService)), BorrowingDto.class);
	}

	public BorrowingDto update(Long id, BorrowingDto dto) {
		validateBooksExists(dto.getBorrowedBooks());
		Borrowing borrowing = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		borrowing.getBorrowedBooks().clear();
		borrowing.getBorrowedBooks().addAll(dto.getBorrowedBooks().stream().map(bbDto -> bbDto.toEntity(borrowing, bookService)).collect(Collectors.toSet()));
		return repository.save(borrowing).toDto();
	}
	
	public void delete(Long id) {
		repository.deleteById(findById(id).getId());
	}
	
	private void validateBooksExists(Set<BorrowedBookDto> set) {
		set.forEach(bbDto -> {
			bookService.findById(bbDto.getBookId());
		});
	}


}
