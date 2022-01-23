package com.liverary.book.springboot.domain.book;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List <Book> findAll(Sort sort);

    //검색
    @Query(value = "SELECT b FROM Book b WHERE b.title like %?1% or b.author like %?1%")
    List <Book> findBySearch( String search);

    @Query("SELECT p FROM Book p ORDER BY p.bookKey DESC")
    List<Book> findAllDesc();
}
