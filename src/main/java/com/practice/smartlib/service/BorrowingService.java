package com.practice.smartlib.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.smartlib.dto.BorrowingDto;
import com.practice.smartlib.model.Borrowing;
import com.practice.smartlib.model.User;
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
		return repository.findAll().stream().map(item -> modelMapper.map(item, BorrowingDto.class)).toList();
	}

	public BorrowingDto findById(Long id) {
		Borrowing borrowing = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(borrowing, BorrowingDto.class);
	}

	public BorrowingDto create(BorrowingDto dto) {
		
		Borrowing borrowing = modelMapper.map(dto, Borrowing.class);
		
		User user = userService.findById(dto.getUserId());
		
		borrowing.setUser(user);
		borrowing.setBorrowDate(LocalDate.now());
		
		borrowing.getBorrowedBooks().forEach(borrowedBook -> {
			borrowedBook.setBorrowing(borrowing);
		});
		
		return modelMapper.map(repository.save(borrowing), BorrowingDto.class);
	}
	
}
