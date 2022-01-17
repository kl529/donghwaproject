package com.liverary.book.springboot.domain.reading;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Builder
public class Readings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private int currentPage;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private int isWrittenBookReport;

    private String bookReport;

    @Column(nullable = false)
    private Long userKey;

    @Column(nullable = false)
    private Long bookKey;

    @Builder
    public Readings(Long id, int currentPage, int score, int isWrittenBookReport, String bookReport, Long userKey, Long bookKey) {
        this.ID = id;
        this.currentPage = currentPage;
        this.score = score;
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;
        this.userKey = userKey;
        this.bookKey = bookKey;
    }

    public void update(int currentPage, int score, int isWrittenBookReport, String bookReport, Long userKey, Long bookKey) {
        this.currentPage = currentPage;
        this.score = score;
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;
        this.userKey = userKey;
        this.bookKey = bookKey;
    }


    public void bookReportUpdate(String bookReport) {
        this.isWrittenBookReport = 1;
        this.bookReport = bookReport;
    }

    public void scoreUpdate(int score) {
        this.score = score;
    }

    public void pageUpdate(int option) {
        if (option == 1){ //1이면 페이지 추가
            this.currentPage++;
        }
        else if(option == 0){
            this.currentPage--;
        }
    }
}