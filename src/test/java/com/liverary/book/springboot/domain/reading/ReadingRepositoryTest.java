package com.liverary.book.springboot.domain.reading;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import com.liverary.book.springboot.domain.user.Role;
import com.liverary.book.springboot.domain.user.User;
import com.liverary.book.springboot.domain.user.UserRepository;
import com.liverary.book.springboot.web.dto.book.BookSaveRequestDto;
import com.liverary.book.springboot.web.dto.reading.ReadingSaveRequestDto;
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
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadingRepositoryTest {

//    @Autowired
//    ReadingRepository readingRepository;
//
//    @Autowired
//    BookRepository bookRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @After
//    public void cleanup() {
//        readingRepository.deleteAll();
//    }

//    @Test
//    public void 게시글저장_불러오기() {
//        //given
//
//        bookRepository.save(Book.builder()
//                .title("테스트 책1")
//                .author("김지원")
//                .publisher("김지원")
//                .country("한국")
//                .bookIntro("재밋음")
//                .bookCover("북커버")
//                .bookContent("책내용")
//                .totalPage(500)
//                .publishedDate(new Date(2022-01-13))
//                .build());
//
//        userRepository.save(User.builder()
//                .email("first_email")
//                .role(Role.ADMIN)
//                .build());
//
//        userRepository.save(User.builder()
//                .email("second_email")
//                .role(Role.ADMIN)
//                .build());
//
//        userRepository.save(User.builder()
//                .email("third_email")
//                .role(Role.ADMIN)
//                .build());
//
//        List<Book> BookList = bookRepository.findAll();
//        Book test1 = BookList.get(0);
//        assertThat(test1.getTitle()).isEqualTo("테스트 책1");
//
//        List<User> UserList = userRepository.findAll();
//        User test_user1 = UserList.get(0);
//        User test_user2 = UserList.get(1);
//        User test_user3 = UserList.get(2);
//
//        assertThat(test_user1.getEmail()).isEqualTo("first_email");
//        assertThat(test_user2.getEmail()).isEqualTo("second_email");
//        assertThat(test_user3.getEmail()).isEqualTo("third_email");
//
//        readingRepository.save(Reading.builder()
//                .book(test1)
//                .user(test_user1)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build());
//
//        readingRepository.save(Reading.builder()
//                .book(test1)
//                .user(test_user2)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build());
//
//        readingRepository.save(Reading.builder()
//                .book(test1)
//                .user(test_user3)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build());
//
//        //when
//        List<Reading> readingList = readingRepository.findAllDescbyUser(1L); //유저 id로 검색
//        assertThat(readingList.size()).isEqualTo(1);
//
//        //when
//        List<Reading> readingList2 = readingRepository.findAllDescbyBook(1L); //책 id로 검색
//        assertThat(readingList2.size()).isEqualTo(3);
//    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;



    @Test
    public void Book_등록된다() throws Exception{
        //given
        String title = "책제목2";
        String author = "저자";
        String publisher = "출판사";
        String bookIntro = "책 소개";
        String bookCover = "책 표지";
        String bookContent = "책 내용";
        String country = "나라";
        int totalPage = 100;
        Date publishedDate = Date.valueOf("2021-01-11");

        BookSaveRequestDto requestDto = BookSaveRequestDto.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .bookIntro(bookIntro)
                .bookCover(bookCover)
                .country(country)
                .bookContent(bookContent)
                .totalPage(totalPage)
                //.registeredDate(registeredDate)
                .publishedDate(publishedDate)
                .build();

        String url = "http://localhost:"+port+"/api/v1/books/admin";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //이하 생략
    }
}