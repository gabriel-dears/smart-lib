package com.practice.smartlib.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowingDto {

	private Long id;
	
	private Long userId;
	
	Set<BorrowedBookDto> borrowedBooks;
	
}
