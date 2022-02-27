package com.liverary.book.springboot.web.dto.book;

import com.liverary.book.springboot.domain.book.Book;
import lombok.Getter;

// 최신 책 가져올 것
@Getter
public class BookIntroDto {
    private Long bookKey;
    private String title;
    private String author;
    private String publisher;

    public BookIntroDto (Book entity){
        this.bookKey= entity.getBookKey();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
    }
}
