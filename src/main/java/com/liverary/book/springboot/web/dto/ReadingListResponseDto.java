package com.liverary.book.springboot.web.dto;

import com.liverary.book.springboot.domain.reading.Readings;
import lombok.Getter;

@Getter
public class ReadingListResponseDto {
    private Long bookkey;
    private Long userkey;
    private int currentpage;
    private int score;
    private boolean iswrittenbookreport;
    private String bookreport;

    public ReadingListResponseDto(Readings entity) {

        this.bookkey = entity.getReadKey().getBookey();
        this.userkey =entity.getReadKey().getUserKey();
        this.currentpage = entity.getCurrentPage();
        this.score = entity.getScore();
        this.iswrittenbookreport = entity.isWrittenBookReport();
        this.bookreport = entity.getBookReport();
    }
}