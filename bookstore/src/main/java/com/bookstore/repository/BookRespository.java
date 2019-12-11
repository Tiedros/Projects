package com.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.Book;

public interface BookRespository extends CrudRepository<Book, Long>{

	List<Book> findByCategory(String category);

	List<Book> findByTitleContaining(String title);

}
