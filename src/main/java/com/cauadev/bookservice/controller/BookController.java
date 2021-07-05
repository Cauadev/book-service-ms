package com.cauadev.bookservice.controller;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.cauadev.bookservice.model.Book;
import com.cauadev.bookservice.proxy.CambioProxy;
import com.cauadev.bookservice.repository.BookRepository;
import com.cauadev.bookservice.response.Cambio;

@RestController
@RequestMapping("/book")
public class BookController {
	
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
		book.setEnviroment(port);
		book.setCurrency(currency);
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		ResponseEntity<Cambio> res = new RestTemplate().getForEntity("http://localhost:8085/cambio"
				+ "/{amount}/{from}/{to}", Cambio.class,params);
		Cambio cambio = res.getBody();
		book.setPrice(cambio.getConvertedValue());
		return book;
		
	}

}
