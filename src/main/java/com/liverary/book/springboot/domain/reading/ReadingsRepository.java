package com.liverary.book.springboot.domain.reading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReadingsRepository extends JpaRepository<Readings, Long> {

    @Query("SELECT p FROM Readings p WHERE p.bookKey = ?1 ORDER BY p.ID DESC")
    List<Readings> findAllDescbyBook(Long idx);

    @Query("SELECT p FROM Readings p WHERE p.bookKey = ?1 ORDER BY p.ID DESC")
    List<Readings> findAllDescbyUser(Long idx);
}