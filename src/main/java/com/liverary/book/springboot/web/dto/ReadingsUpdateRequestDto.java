package com.liverary.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingsUpdateRequestDto {
    private Long bookKey;
    private Long userKey;
    private int currentPage;
    private int score;
    private int isWrittenBookReport;
    private String bookReport;

    @Builder
    public ReadingsUpdateRequestDto(long bookKey, long userKey, int currentPage, int score, int isWrittenBookReport, String bookReport) {
        this.bookKey = bookKey;
        this.userKey = userKey;
        this.currentPage = currentPage;
        this.score = score;
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;
    }
}
