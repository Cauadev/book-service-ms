package com.cauadev.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookServiceMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceMsApplication.class, args);
	}

}
