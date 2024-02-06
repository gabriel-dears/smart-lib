package com.practice.smartlib.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.practice.smartlib.dto.BorrowingDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "borrowing", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BorrowedBook> borrowedBooks = new HashSet<>();

    private LocalDate borrowDate;
    private LocalDate returnDate;

    // Constructors, getters, setters, and other methods

    public void addBorrowedBook(BorrowedBook borrowedBook) {
        borrowedBooks.add(borrowedBook);
        borrowedBook.setBorrowing(this);
    }

    public void removeBorrowedBook(BorrowedBook borrowedBook) {
        borrowedBooks.remove(borrowedBook);
        borrowedBook.setBorrowing(null);
    }

	public BorrowingDto toDto() {
		BorrowingDto dto = new BorrowingDto();
		
		dto.setId(id);
		dto.setUserId(user.getId());
		dto.setBorrowDate(borrowDate);
		dto.setReturnDate(returnDate);
		dto.setBorrowedBooks(borrowedBooks.stream().map(BorrowedBook::toDto).collect(Collectors.toSet()));
		
		return dto;
	}
}
