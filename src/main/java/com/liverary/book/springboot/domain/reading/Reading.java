package com.liverary.book.springboot.domain.reading;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity(name = "reading")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readingKey;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_key")
    private Book book;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_key")
    private User user;

    private int score;

    @Column(nullable = false)
    private int isWrittenBookReport = 0;

    @Column(length = 100)
    private String bookReport;
}
