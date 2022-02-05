package com.liverary.book.springboot.web;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.reading.ReadingRepository;
import com.liverary.book.springboot.domain.user.User;
import com.liverary.book.springboot.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;



    @Test
    public void 게시글저장불러오기(){
        String title = "책제목3";
        String author = "저자3";
        String publisher = "출판사";
        String bookIntro = "책 소개";
        String bookCover = "책 표지";
        String bookContent = "책 내용";
        String country = "나라";
        int totalPage = 100;
        Date publishedDate = Date.valueOf("2021-01-11");


        bookRepository.save(Book.builder().title(title).author(author).publisher(publisher).country(country).bookIntro(bookIntro).bookCover(bookCover).bookContent(bookContent).publishedDate(publishedDate).build());
        List<Book> bookList = bookRepository.findAll();

        Book book = bookList.get(0);
        assertThat(book.getTitle()).isEqualTo(title);


    }


}




