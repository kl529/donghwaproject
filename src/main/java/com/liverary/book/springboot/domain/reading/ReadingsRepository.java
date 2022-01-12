package com.liverary.book.springboot.domain.reading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReadingsRepository extends JpaRepository<Readings, Long> {

    @Query("SELECT p FROM Reading p ORDER BY p.id DESC")
    List<Readings> findAllDesc();


}