package com.liverary.book.springboot.domain.file;

import com.liverary.book.springboot.domain.book.Book;
import lombok.*;

import javax.persistence.*;
@Getter
@Entity
@NoArgsConstructor
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public Files(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
