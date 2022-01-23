package com.liverary.book.springboot.domain.reading;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"book_key","user_key"}))
@Entity(name = "reading")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readingKey;

    @ManyToOne
    @JoinColumn(name = "book_key")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_key")
    private User user;

    private int score;

    @Column(nullable = false)
    private int isWrittenBookReport = 0;

    @Column(length = 100)
    private String bookReport;

    @Builder
    public Reading(Book book , User user, int score, int isWrittenBookReport, String bookReport){
        this.book = book ;
        this.user = user;
        this.score = score;
        this.isWrittenBookReport = isWrittenBookReport;
        this.bookReport = bookReport;

    }
    public void setUser (User user){
        this.user = user;
    }
    public void setBook (Book book){
        this.book = book;
    }
}
