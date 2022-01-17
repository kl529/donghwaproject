package com.liverary.book.springboot.web.dto;

import com.liverary.book.springboot.domain.reading.Readings;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingsSaveRequestDto { //StartReading!! -> 유저가 책을 읽기 시작함 -> readings에 Row 삽입
    private Long bookKey;
    private Long userKey;
    private int currentPage;
    private int score;
    private int isWrittenBookReport;
    private String bookReport;

    @Builder
    public ReadingsSaveRequestDto(long bookKey, long userKey, int currentPage, int score, int isWrittenBookReport, String bookReport) {
        this.bookKey = bookKey;
        this.userKey = userKey;
        this.currentPage = 1;
        this.score = 0;
        this.isWrittenBookReport = 0;
        this.bookReport = "";
    }

    public Readings toEntity() {
        return Readings.builder()
                .bookKey(bookKey)
                .userKey(userKey)
                .currentPage(currentPage)
                .score(score)
                .isWrittenBookReport(isWrittenBookReport)
                .bookReport(bookReport)
                .build();
    }

}