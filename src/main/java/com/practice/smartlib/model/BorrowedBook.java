package com.practice.smartlib.model;

import com.practice.smartlib.dto.BorrowedBookDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    private int quantity;

    @ManyToOne
    private Borrowing borrowing;

	public BorrowedBookDto toDto() {
		BorrowedBookDto dto = new BorrowedBookDto();

		dto.setId(id);
		dto.setBookId(book.getId());
		dto.setBookTitle(book.getTitle());
		dto.setQuantity(quantity);
		dto.setAuthorName(book.getAuthor().getName());
		dto.setCategoryName(book.getCategory().getName());
		
		return dto;
	}

    // Constructors, getters, setters, and other methods
}
