package com.practice.smartlib.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.smartlib.dto.BookDto;
import com.practice.smartlib.model.Author;
import com.practice.smartlib.model.Book;
import com.practice.smartlib.model.Category;
import com.practice.smartlib.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BookDto create(BookDto dto) {
		
		if( repository.findByTitle(dto.getTitle()).isPresent() ) {
			throw new RuntimeException("The informed title already exists!");
		}
		
		Book book = modelMapper.map(dto, Book.class);
		
		Author author = authorService.findBydId(dto.getAuthorId());
		Category category = categoryService.findById(dto.getCategoryId());
		
		book.setAuthor(author);
		book.setCategory(category);
		
		Book createdBook = repository.save(book);
		BookDto createdDto = modelMapper.map(createdBook, BookDto.class);
		return createdDto;
	}

	public List<BookDto> findAll() {
		return repository.findAll().stream().map(item -> modelMapper.map(item, BookDto.class)).toList();
	}

	public Book findById(Long bookId) {
		return repository.findById(bookId).orElseThrow(() -> new EntityNotFoundException());
	}
	
}
