package com.liverary.book.springboot.web;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.reading.ReadingRepository;
import com.liverary.book.springboot.domain.user.User;
import com.liverary.book.springboot.domain.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReadingRepository readingRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 게시글저장불러오기(){
        String title = "title4";
        String author = "author2";
        String country = "country2";
        String publisher = "publisher2";
        String bookContent = "content2";
        String bookCover = "cover2";
        String bookIntro = "bookIntro2";
        int totalPage = 4;
        Date publishedDate = Date.valueOf("2020-01-30");

        bookRepository.save(Book.builder().title(title).author(author).publisher(publisher).country(country).bookIntro(bookIntro).bookCover(bookCover).bookContent(bookContent).publishedDate(publishedDate).build());


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
    @Test
    public void set_up(){
        Book book = Book.builder().title("this is title1").author("author3").publisher("publisher3").bookIntro("ddd").bookCover("bookCover").bookContent("here once lived two brothers named Ali Baba and \n" +
                "Cassim. They came from a very poor family. Both of the \n" +
                "brothers were married. Ali Baba lived with his poor wife in \n" +
                "a small house. He had to cut down trees to sell in the \n" +
                "marketplace for money. Cassim lived in a big house \n" +
                "because he married a rich girl. Her family had a lot of " ).publishedDate(Date.valueOf("2022-11-23")).build();
        bookRepository.save(book);
        User user = User.builder().email("didddmstj98@naver.com").build();
        userRepository.save(user);
    }
    @Test
    public void save_Reading(){
        Book book = bookRepository.findAll().get(0);
        User user = userRepository.findAll().get(1);
        readingRepository.save(Reading.builder().book(book).user(user).score(10).bookReport("bookReport").isWrittenBookReport(0).build());
    }
    @Test
    public void delete_Book(){
        Book book = bookRepository.findAll().get(1);
        bookRepository.delete(book);
    }
    @Test
    public void delete_User(){
        User user = userRepository.findAll().get(0);
        userRepository.delete(user);
    }


}
