package com.practice.smartlib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.smartlib.model.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

}
