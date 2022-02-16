package com.liverary.book.springboot.domain.book;

import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "book")
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookKey;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String author;

    @Column(length = 500, nullable = false)
    private String publisher;

    private String country;

    @Column(length = 500, nullable = false)
    private String bookIntro;

    @Column(length = 500 , nullable = false)
    private String bookCover;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String bookContent;

    @Column(nullable = false)
    private int totalPage;

    @Column(nullable = false)
    private Date publishedDate;

    @OneToMany(mappedBy = "book", targetEntity = Reading.class, cascade = CascadeType.REMOVE)
    private List<Reading> list ;

    @Column
    private Long fileId;
    @Builder
    public Book(String title, String author, String publisher, String country, String bookIntro, String bookCover, String bookContent, int totalPage, Date publishedDate, Long fileId){
        this.title = title ;
        this.author = author;
        this.publisher = publisher;
        this.country = country;
        this.bookIntro  = bookIntro;
        this.bookCover = bookCover;
        this.bookContent = bookContent;
        this.publishedDate = publishedDate;
        this.totalPage= totalPage;
        this.fileId = fileId;
    }
    public void update (String bookIntro, String bookContent, String bookCover){
        this.bookIntro = bookIntro;
        this.bookContent = bookContent;
        this.bookCover = bookCover;
    }
}
