package com.liverary.book.springboot.web.dto.book;

import com.liverary.book.springboot.domain.book.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Setter
@Getter
@NoArgsConstructor
public class BookSaveRequestDto {
    private String title;
    private String author;
    private String publisher;
    private String country;
    private String bookIntro;
    private String bookCover;
    private String bookContent;
    private int totalPage;
    private Date publishedDate;
    private Long fileId;
    @Builder
    public BookSaveRequestDto(String title, String author, String publisher, String country,
                              String bookIntro, String bookContent, int totalPage,
                              Date publishedDate, Long fileId){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.country = country;
        this.bookIntro = bookIntro;
        this.bookContent  =bookContent;
        this.totalPage = totalPage;
        this.publishedDate = publishedDate;
        this.fileId = fileId;
    }
    public Book toEntity(){
        return Book.builder().title(title).author(author).publisher(publisher).country(country).bookIntro(bookIntro)
            .bookContent(bookContent).totalPage(totalPage).publishedDate(publishedDate).fileId(fileId).build();
    }
}
