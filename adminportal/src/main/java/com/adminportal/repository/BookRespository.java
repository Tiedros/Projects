package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Book;

public interface BookRespository extends CrudRepository<Book, Long>{

}
