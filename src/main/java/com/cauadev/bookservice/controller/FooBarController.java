package com.cauadev.bookservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
@RequestMapping("/book")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/foo-bar")
//	@Retry(name = "foo", fallbackMethod = "fallbackMethod")
//	@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
//	@RateLimiter(name = "default", fallbackMethod = "fallbackMethod")
	@Bulkhead(name = "default", fallbackMethod = "fallbackMethod")
	public String fooBar() {
//		logger.info("request to foo bar is received");
//		var response = new RestTemplate()
//				.getForEntity("http://localhost:8080/foo-bar"
//							  , String.class);
//		
//		return response.getBody();
		return "foo-bar response";
	}
	
	public String fallbackMethod(Exception ex) {
		return "fallbackMethod foo-bar";
	}

}
