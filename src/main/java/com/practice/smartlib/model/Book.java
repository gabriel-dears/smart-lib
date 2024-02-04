package com.practice.smartlib.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Author author;

    private int totalCopies;
    private int availableCopies;

    @ManyToOne
    private Category category;

    // Constructors, getters, setters, and other methods

    @PrePersist
    public void updateAvailableCopiesBeforePersist() {
        if (availableCopies == 0) {
            availableCopies = totalCopies;
        }
    }
}
