package com.liverary.book.springboot.domain.reading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    @Query("SELECT p FROM reading p WHERE p.book.bookKey = ?1 ORDER BY p.readingKey DESC")
    List<Reading> findAllDescbyBook(Long idx);

    @Query("SELECT p FROM reading p WHERE p.user.userKey = ?1 ORDER BY p.readingKey DESC")
    List<Reading> findAllDescbyUser(Long idx);
}