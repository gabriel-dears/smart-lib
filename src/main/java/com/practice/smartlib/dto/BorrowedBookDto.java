package com.practice.smartlib.dto;

import java.util.Objects;

import com.practice.smartlib.model.BorrowedBook;
import com.practice.smartlib.model.Borrowing;
import com.practice.smartlib.service.BookService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowedBookDto {

	private Long id;
	private String bookTitle;
	private Long bookId;
	private int quantity;
	private String authorName;
	private String categoryName;
	
	public BorrowedBook toEntity(Borrowing borrowing, BookService bookService) {
		BorrowedBook borrowedBook = new BorrowedBook();
		
		borrowedBook.setBorrowing(borrowing);
		borrowedBook.setQuantity(quantity);
		borrowedBook.setBook(bookService.findById(bookId));
		
		return borrowedBook;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BorrowedBookDto other = (BorrowedBookDto) obj;
		return Objects.equals(bookId, other.bookId);
	}
	
	
	
}
