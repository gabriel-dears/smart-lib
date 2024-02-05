package com.practice.smartlib.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowedBookDto {

	private Long id;
	private BookDto book;
	private int quantity;
}
