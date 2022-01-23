package com.liverary.book.springboot.web.dto.book;

import com.liverary.book.springboot.domain.book.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;


@Getter
public class BookListResponseDto {
    // 책 목록 표시에 사용할 항목
    private Long id;
    private String title;
    private String author;
    private String publisher;

    public BookListResponseDto(Book entity){
        this.id = entity.getBookKey();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
    }
}
