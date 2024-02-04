package com.practice.smartlib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.smartlib.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	public Optional<Author> findByCpf(String cpf);
	
}
