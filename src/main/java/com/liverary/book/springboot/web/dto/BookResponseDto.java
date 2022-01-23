package com.liverary.book.springboot.web.dto;

import com.liverary.book.springboot.domain.book.Book;
import lombok.Getter;

import java.sql.Date;

@Getter
public class BookResponseDto {
    private Long bookKey;
    private String title;
    private String author;
    private String publisher;
    private String country;
    private String bookIntro;
    private String bookCover;
    private String bookContent;
    private int totalPage;
    private Date registeredDate;
    private Date publishedDate;

    public BookResponseDto (Book entity){
        this.bookKey  = entity.getBookKey();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.country = entity.getCountry();
        this.bookIntro = entity.getBookIntro();
        this.bookCover = entity.getBookCover();
        this.bookContent = entity.getBookContent();
        this.totalPage = entity.getTotalPage();
        this.registeredDate = entity.getRegisteredDate();
        this.publishedDate = entity.getPublishedDate();
    }
}
