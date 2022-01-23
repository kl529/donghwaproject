package com.liverary.book.springboot.domain.book;

<<<<<<< HEAD
import com.liverary.book.springboot.domain.reading.Reading;
=======
import com.liverary.book.springboot.domain.BaseTimeEntity;
>>>>>>> origin/ykm
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
<<<<<<< HEAD
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "book")

public class Book {
=======

@Getter
@NoArgsConstructor
@Entity
public class Book extends BaseTimeEntity {
>>>>>>> origin/ykm
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookKey;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String author;

    @Column(length = 500, nullable = false)
    private String publisher;

    private String country;

    @Column(length = 500, nullable = false)
    private String bookIntro;

    @Column(length = 500 , nullable = false)
    private String bookCover;

<<<<<<< HEAD
    @Column(columnDefinition = "TEXT")
=======
    @Column(nullable = false)
>>>>>>> origin/ykm
    private String bookContent;

    @Column(nullable = false)
    private int totalPage;

<<<<<<< HEAD
    @Column(nullable = false)
    private Date registeredDate;
=======
//    @Column(nullable = false)
//    private Date registeredDate;
>>>>>>> origin/ykm

    @Column(nullable = false)
    private Date publishedDate;

<<<<<<< HEAD
    @OneToMany(mappedBy = "book",cascade = CascadeType.REMOVE)
    private List<Reading> list ;

=======
>>>>>>> origin/ykm
    @Builder
    public Book(String title, String author, String publisher, String country, String bookIntro, String bookCover, String bookContent, int totalPage, Date registeredDate, Date publishedDate){
        this.title = title ;
        this.author = author;
        this.publisher = publisher;
        this.country = country;
        this.bookIntro  = bookIntro;
        this.bookCover = bookCover;
        this.bookContent = bookContent;
<<<<<<< HEAD
        this.registeredDate = registeredDate;
=======
        //this.registeredDate = registeredDate;
>>>>>>> origin/ykm
        this.publishedDate = publishedDate;
        this.totalPage= totalPage;
    }
}
