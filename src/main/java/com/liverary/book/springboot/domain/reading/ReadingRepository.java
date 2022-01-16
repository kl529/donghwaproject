package com.liverary.book.springboot.domain.reading;

import com.liverary.book.springboot.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading,Long>
{

}

