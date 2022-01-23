package com.liverary.book.springboot.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT p FROM books p ORDER BY p.id DESC")
    List<Book> findAllDesc();
}
