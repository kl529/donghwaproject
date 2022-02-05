package com.liverary.book.springboot.domain.reading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    @Query("SELECT p FROM reading p WHERE p.book.bookKey = ?1 ORDER BY p.readingKey DESC")
//    @Query(value = "SELECT p FROM reading p WHERE p.book.book_id = ?1 ORDER BY p.readingKey DESC", nativeQuery = true)

    List<Reading> findAllDescbyBook(Long idx);

    @Query("SELECT p FROM reading p WHERE p.user.userKey = ?1 ORDER BY p.readingKey DESC")
//    @Query(value = "SELECT p FROM reading p WHERE p.user.user_id = ?1 ORDER BY p.readingKey DESC", nativeQuery = true)
    List<Reading> findAllDescbyUser(Long idx);
}