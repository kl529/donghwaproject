package com.liverary.book.springboot.domain.book;


import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @After
    public void cleanup(){
        bookRepository.deleteAll();
    }

    @Test
    public void 저장된책_불러오기(){
        //given
        String title = "책제목";
        String author = "저자";
        String publisher = "출판사";
        String bookIntro = "책 소개";
        String bookCover = "책 표지";
        String bookContent = "책 내용";
        int totalPage = 100;
        //Date registeredDate = Date.valueOf("2022-01-19");
        Date publishedDate = Date.valueOf("2021-01-11");

        bookRepository.save(Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .bookIntro(bookIntro)
                .bookCover(bookCover)
                .bookContent(bookContent)
                .totalPage(totalPage)
                //.registeredDate(registeredDate)
                .publishedDate(publishedDate)
                .build()
        );

        //when
        List<Book> bookList = bookRepository.findAll();

        //then
        Book book = bookList.get(0);
        assertThat(book.getTitle()).isEqualTo(title);
        assertThat(book.getTotalPage()).isEqualTo(totalPage);
        //이하 생략
        assertThat(book.getPublishedDate()).isEqualTo(publishedDate);

    }

    @Test
    public void BaseTimeEntity_등록(){
        // given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        bookRepository.save(Book.builder()
                .title("title")
                .author("author")
                .publisher("publisher")
                .bookIntro("bookIntro")
                .bookCover("bookCover")
                .bookContent("bookContent")
                .totalPage(1)
                //.registeredDate(registeredDate)
                .publishedDate(Date.valueOf("2019-01-20"))
                .build()
        );

        //when
        List<Book> bookList = bookRepository.findAll();

        //then
        Book book = bookList.get(0);

        System.out.println(">>>>> createDate="+book.getCreatedDate());

        assertThat(book.getCreatedDate()).isAfter(now);
        assertThat(book.getModifiedDate()).isAfter(now);
    }
}
