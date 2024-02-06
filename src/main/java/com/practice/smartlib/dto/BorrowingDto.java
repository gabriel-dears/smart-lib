package com.practice.smartlib.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.practice.smartlib.model.Borrowing;
import com.practice.smartlib.model.User;
import com.practice.smartlib.service.BookService;
import com.practice.smartlib.service.UserService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowingDto {

	private Long id;
	
	private Long userId;
	
	private LocalDate borrowDate;
    private LocalDate returnDate;
	
	Set<BorrowedBookDto> borrowedBooks;
	
	public Borrowing toEntity(BorrowingDto dto, UserService userService, BookService bookService) {
		Borrowing borrowing = new Borrowing();

		User user = userService.findById(dto.getUserId());

		borrowing.setUser(user);
		borrowing.setBorrowDate(LocalDate.now());

		borrowing.setBorrowedBooks(dto.getBorrowedBooks().stream().map(bDto -> bDto.toEntity(borrowing, bookService))
				.collect(Collectors.toSet()));
		return borrowing;
	}
	
}
