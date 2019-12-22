package com.bookstore.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/book")
public class BookResource {
	
private static final Logger LOG=LoggerFactory.getLogger(BookResource.class);
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Book addBookPost(@RequestBody Book book) {
		LOG.info("******** IN Side Method {} ********","addBookPost");
		return bookService.save(book);
	}
	
	@RequestMapping(value="/add/image",method=RequestMethod.POST)
	public ResponseEntity upload(
				@RequestParam("id") Long id,
				HttpServletResponse response,HttpServletRequest request
			) {
		LOG.info("******** IN Side Method {} ********","upload");
		try {
			Book book=bookService.findOne(id);
			MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
			Iterator<String> it=multipartRequest.getFileNames();
			MultipartFile multipartFile=multipartRequest.getFile(it.next());
			String fileName=id+".png";
			
			byte[] bytes= multipartFile.getBytes();
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();
			return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/bookList")
	public List<Book> getBookList(){
		LOG.info("******** IN Side Method {} ********","getBookList");
		return bookService.findAll();
	}
	
	

	@RequestMapping("/{id}")
	public Book getBook(@PathVariable("id") Long id) {
		LOG.info("******** IN Side Method {} ********","getBook");
	return bookService.findOne(id);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Book updateBookPost(@RequestBody Book book) {
		LOG.info("******** IN Side Method {} ********","updateBookPost");
		return bookService.save(book);
	}
	
	@RequestMapping(value="/update/image",method=RequestMethod.POST)
	public ResponseEntity updateImagePost(
			@RequestParam("id") Long id,
			HttpServletResponse response,HttpServletRequest request
		) {
	LOG.info("******** IN Side Method {} ********","updateImagePost");
	try {
		Book book=bookService.findOne(id);
		MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
		Iterator<String> it=multipartRequest.getFileNames();
		MultipartFile multipartFile=multipartRequest.getFile(it.next());
		String fileName=id+".png";
		
		Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
		
		byte[] bytes= multipartFile.getBytes();
		BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
		stream.write(bytes);
		stream.close();
		return new ResponseEntity(HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
}
}
