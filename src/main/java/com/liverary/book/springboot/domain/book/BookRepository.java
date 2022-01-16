package com.liverary.book.springboot.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "SELECT b FROM book b ORDER BY b.registered_date DESC ", nativeQuery = true)
    List <Book> findAllDesc();
}
