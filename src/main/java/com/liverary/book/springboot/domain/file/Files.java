package com.liverary.book.springboot.domain.file;

import com.liverary.book.springboot.domain.book.Book;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int fno;

    String filename;
    String fileOriName;
    String fileurl;

    @OneToOne(mappedBy = "files")
    private Book book;
}
