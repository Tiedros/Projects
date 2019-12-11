package com.adminportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Book;
import com.adminportal.repository.BookRespository;
import com.adminportal.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRespository bookRespository;

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRespository.save(book);
	}

	@Override
	public List<Book> findAll() {
		
		return (List<Book>) bookRespository.findAll();
	}

	@Override
	public Optional<Book> findOne(Long id) {
		// TODO Auto-generated method stub
		return bookRespository.findById(id);
	}

	@Override
	public void removeOne(long id) {
		// TODO Auto-generated method stub
		bookRespository.deleteById(id);
		
	}

}
