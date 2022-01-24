package com.liverary.book.springboot.web;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import com.liverary.book.springboot.web.dto.book.BookSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApiControllerTest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private BookRepository bookRepository;
    
    @After
    public void tearDown() throws Exception{
        bookRepository.deleteAll();
    }
    
    @Test
    public void Book_등록된다() throws Exception{
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

        BookSaveRequestDto requestDto = BookSaveRequestDto.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .bookIntro(bookIntro)
                .bookCover(bookCover)
                .bookContent(bookContent)
                .totalPage(totalPage)
                //.registeredDate(registeredDate)
                .publishedDate(publishedDate)
                .build();

        String url = "http://localhost:"+port+"api/v1/books/admin";
        
        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        
        List<Book> all = bookRepository.findAll();
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
        assertThat(all.get(0).getBookContent()).isEqualTo(bookContent);
        //이하 생략
    }
}
