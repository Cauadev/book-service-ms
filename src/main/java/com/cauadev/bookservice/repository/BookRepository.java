package com.cauadev.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cauadev.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
