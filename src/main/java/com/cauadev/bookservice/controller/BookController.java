package com.cauadev.bookservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cauadev.bookservice.model.Book;
import com.cauadev.bookservice.proxy.CambioProxy;
import com.cauadev.bookservice.repository.BookRepository;
import com.cauadev.bookservice.response.Cambio;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private CambioProxy proxy;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@GetMapping("/{id}/{currency}")
	public Book findBook(
			@PathVariable Long id,
			@PathVariable String currency) {
		 Book book = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
		var port = environment.getProperty("local.server.port");
		book.setCurrency(currency);
		Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		book.setEnviroment("Book port: "+port+ " Cambio port: "+cambio.getEnviroment());
		book.setPrice(cambio.getConvertedValue());
		return book;
	}

}
