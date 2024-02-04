package com.practice.smartlib.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

	private Long id;
	private String title;
	private Long authorId;
	private int totalCopies;
	private int availableCopies;
	private Long categoryId;
	
}
