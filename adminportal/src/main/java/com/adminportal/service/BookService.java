package com.adminportal.service;

import java.util.List;
import java.util.Optional;

import com.adminportal.domain.Book;

public interface BookService {
	Book save(Book book);

	List<Book> findAll();

	Optional<Book> findOne(Long id);

	void removeOne(long id);
}
