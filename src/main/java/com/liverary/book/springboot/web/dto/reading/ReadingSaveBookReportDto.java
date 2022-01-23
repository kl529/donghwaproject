package com.liverary.book.springboot.web.dto.reading;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingSaveBookReportDto {
    private String bookReport;

    @Builder
    public ReadingSaveBookReportDto(String bookReport) {
        this.bookReport = bookReport;
    }
}