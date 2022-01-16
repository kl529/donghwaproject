package com.liverary.book.springboot.web.domain.book;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;


    @Test
    public void 게시글저장_불러오기(){
        String title = "title1";
        String author = "author1";
        String country = "country";
        String publisher = "publisher";
        String bookContent = "content";
        String bookCover = "cover";
        String bookIntro = "bookIntro";
        int totalPage = 4;
        Date registeredDate = Date.valueOf("2020-01-01");
        Date publishedDate = Date.valueOf("2020-01-30");

        bookRepository.save(Book.builder().title(title).author(author).publisher(publisher).country(country).bookIntro(bookIntro).bookCover(bookCover).bookContent(bookContent).registeredDate(registeredDate).publishedDate(publishedDate).build());


    }
    @Test
    public void selectDummies(){
        Long id = 1L;
        Optional<Book> result = bookRepository.findById(id);
        if (result.isPresent()){
            Book book = result.get();
            System.out.println(book);
        }
    }

}
