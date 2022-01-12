package com.liverary.book.springboot.domain.reading;

import com.liverary.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Readings extends BaseTimeEntity {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ReadingId ReadKey;

    @Column(length = 1000, nullable = false)
    private int currentPage;

    @Column(length = 5, nullable = false)
    private int score;

    @Column(nullable = false)
    private boolean isWrittenBookReport = false;

    @Column(length = 1000, nullable = false)
    private String bookReport;

    @Builder
    public Readings(int currentPage, int score, boolean isWrittenBookReport, String bookReport) {
        this.currentPage = currentPage;
        this.score = score;
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;
    }

    public void pageUpdate(int currentPage) {
        this.currentPage = currentPage;
    }
    public void detailUpdate(boolean isWrittenBookReport, String bookReport) {
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;
    }
}