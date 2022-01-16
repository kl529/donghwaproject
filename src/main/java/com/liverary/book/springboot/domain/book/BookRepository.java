package com.liverary.book.springboot.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    //@Query("SELECT b FROM BOOK b ORDERED BY b.registeredDate DESC")
    //List<Book> findAllDesc();
}
