package com.liverary.book.springboot.domain.reading;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "reading", uniqueConstraints = {@UniqueConstraint(
        name = "KEYS_UNIQUE", columnNames = {"book_key", "user_key"})})
@Entity(name = "reading")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readingKey;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_key")
    private User user;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_key")
    private Book book;

    @Column(nullable = false)
    private int currentPage;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private int isWrittenBookReport = 0;

    @Column(length = 100)
    private String bookReport;

    @Builder
    public Reading(Book book , User user, int currentPage, int score, int isWrittenBookReport, String bookReport) {
        this.book = book ;
        this.user = user;
        this.currentPage = currentPage;
        this.score = score;
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;
    }

    public void update(Book book , User user, int currentPage, int score, int isWrittenBookReport, String bookReport) {
        this.book = book ;
        this.user = user;
        this.currentPage = currentPage;
        this.score = score;
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;
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

    public void setUser (User user){
        this.user = user;
    }
    public void setBook (Book book){
        this.book = book;
    }
}