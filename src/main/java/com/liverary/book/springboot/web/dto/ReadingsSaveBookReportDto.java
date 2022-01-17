package com.liverary.book.springboot.web.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingsSaveBookReportDto {
    private String bookReport;

    @Builder
    public ReadingsSaveBookReportDto(String bookReport) {
        this.bookReport = bookReport;
    }
}