package com.liverary.book.springboot.web.dto;

import com.liverary.book.springboot.domain.book.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookUpdateRequestDto {
    private  String bookIntro;
    private String bookCover;
    private String bookContent;

    @Builder
    public BookUpdateRequestDto(String bookIntro, String bookCover, String bookContent){
        this.bookContent = bookContent;
        this.bookCover = bookCover;
        this.bookIntro = bookIntro;
    }
}
