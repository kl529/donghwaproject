package com.liverary.book.springboot.domain.reading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReadingsRepository extends JpaRepository<Readings, ReadingId> {

    @Query("SELECT p FROM Readings p ORDER BY p.ReadKey.bookey DESC")
    List<Readings> findAllDesc();


}