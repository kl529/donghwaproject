package com.liverary.book.springboot.web.dto.reading;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingSaveRequestDto { //StartReading!! -> 유저가 책을 읽기 시작함 -> reading에 Row 삽입
    private Book book;
    private User user;
    private int currentPage;
    private int score;
    private int isWrittenBookReport;
    private String bookReport;

    @Builder
    public ReadingSaveRequestDto(Book book, User user, int currentPage, int score, int isWrittenBookReport, String bookReport) {
        this.book = book;
        this.user = user;
        this.currentPage = 1;
        this.score = 0;
        this.isWrittenBookReport = 0;
        this.bookReport = "";
    }

    public Reading toEntity() {
        return Reading.builder()
                .book(book)
                .user(user)
                .currentPage(currentPage)
                .score(score)
                .isWrittenBookReport(isWrittenBookReport)
                .bookReport(bookReport)
                .build();
    }

}