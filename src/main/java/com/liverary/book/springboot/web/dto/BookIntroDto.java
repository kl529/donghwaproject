package com.liverary.book.springboot.web.dto;

import com.liverary.book.springboot.domain.book.Book;
import lombok.Getter;

// 최신 책 가져올 것
@Getter
public class BookIntroDto {
    private String title;
    private String bookCover;
    private String author;
    private String publsher;

    public BookIntroDto (Book entity){
        this.title = entity.getTitle();
        this.bookCover = entity.getBookCover();
        this.author = entity.getAuthor();
        this.publsher = entity.getPublisher();
    }
}
