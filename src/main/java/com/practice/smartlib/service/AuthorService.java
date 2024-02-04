package com.practice.smartlib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.smartlib.model.Author;
import com.practice.smartlib.repository.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository repository;

	public List<Author> findAll() {
		return repository.findAll();
	}

	public Author findBydId(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public Author insert(Author dto) {
		if( repository.findByCpf(dto.getCpf()).isPresent() ) {
			throw new RuntimeException("Cpf already registered!");
		}
		return repository.save(dto);
	}

	public Author update(Long id, @Valid Author dto) {
		
		Author foundAuthor = findBydId(id);
		
		foundAuthor.setName(dto.getName());
		foundAuthor.setCpf(dto.getCpf());
		
		return repository.save(foundAuthor);
	}

	public void delete(Long id) {
		repository.delete(findBydId(id));
	}
	
	

}
