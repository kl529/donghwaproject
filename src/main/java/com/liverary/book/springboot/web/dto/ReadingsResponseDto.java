package com.liverary.book.springboot.web.dto;

import com.liverary.book.springboot.domain.reading.Readings;
import lombok.Getter;

@Getter
public class ReadingsResponseDto {
    private Long ID;
    private Long bookKey;
    private Long userKey;
    private int currentpage;
    private int score;
    private int iswrittenbookreport;
    private String bookreport;

    public ReadingsResponseDto(Readings entity) {

        this.ID = entity.getID();
        this.bookKey = entity.getBookKey();
        this.userKey = entity.getUserKey();
        this.currentpage = entity.getCurrentPage();
        this.score = entity.getScore();
        this.iswrittenbookreport = entity.getIsWrittenBookReport();
        this.bookreport = entity.getBookReport();
    }
}