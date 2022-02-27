package com.liverary.book.springboot.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;

public interface FileRepository extends JpaRepository<Files, Long> {

}
