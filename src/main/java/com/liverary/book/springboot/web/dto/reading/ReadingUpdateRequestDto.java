package com.liverary.book.springboot.web.dto.reading;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingUpdateRequestDto {
    private Book book;
    private User user;
    private int currentPage;
    private int score;
    private int isWrittenBookReport;
    private String bookReport;

    @Builder
    public ReadingUpdateRequestDto(Book book, User user, int currentPage, int score, int isWrittenBookReport, String bookReport) {
        this.book = book;
        this.user = user;
        this.currentPage = currentPage;
        this.score = score;
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;
    }
}
