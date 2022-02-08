package com.liverary.book.springboot.web;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import com.liverary.book.springboot.domain.reading.ReadingRepository;
import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.user.Role;
import com.liverary.book.springboot.domain.user.User;
import com.liverary.book.springboot.web.dto.book.BookSaveRequestDto;
import com.liverary.book.springboot.web.dto.reading.ReadingCalcCurrentPageDto;
import com.liverary.book.springboot.web.dto.reading.ReadingGiveScoreDto;
import com.liverary.book.springboot.web.dto.reading.ReadingSaveBookReportDto;
import com.liverary.book.springboot.web.dto.reading.ReadingSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

// For mockMvc

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadingApiControllerTest {

//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private ReadingRepository readingRepository;
//
//    @After
//    public void tearDown() throws Exception {
//        readingRepository.deleteAll();
//    }

//    @Test
//    public void StartReading_test() throws Exception { // 책 읽기 시작
//        //given
//        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
//        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);
//
//        ReadingSaveRequestDto requestDto = ReadingSaveRequestDto.builder()
//                .book(test_book)
//                .user(test_user)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/reading/startreading";
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        //then
//        List<Reading> all = readingRepository.findAll();
//        assertThat(all.get(0).getBook().getTitle()).isEqualTo(test_book.getTitle());
//        assertThat(all.get(0).getUser().getEmail()).isEqualTo(test_user.getEmail());
//        assertThat(all.get(0).getCurrentPage()).isEqualTo(1);
//        assertThat(all.get(0).getScore()).isEqualTo(0);
//        assertThat(all.get(0).getIsWrittenBookReport()).isEqualTo(0);
//        assertThat(all.get(0).getBookReport()).isEqualTo("");
//    }
//
//    @Test
//    public void SaveBookReport_test() throws Exception { //BookReport 수정
//        //given
//        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
//        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);
//
//        Reading savedReadings = readingRepository.save(Reading.builder()
//                .book(test_book)
//                .user(test_user)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build());
//
//        Long update_reading_key = savedReadings.getReadingKey();
//        String expectedContent = "변경완료";
//
//        ReadingSaveBookReportDto requestDto = ReadingSaveBookReportDto.builder()
//                .bookReport(expectedContent)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/reading/savebookreport/" + update_reading_key;
//
//        //when
//        HttpEntity<ReadingSaveBookReportDto> requestEntity = new HttpEntity<>(requestDto);
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        //then
//        List<Reading> all = readingRepository.findAll();
//        assertThat(all.get(0).getCurrentPage()).isEqualTo(1);
//        assertThat(all.get(0).getBookReport()).isEqualTo(expectedContent);
//
//    }
//
//    @Test
//    public void GiveScore_test() throws Exception { //점수 수정
//        //given
//        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
//        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);
//
//        Reading savedReadings = readingRepository.save(Reading.builder()
//                .book(test_book)
//                .user(test_user)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build());
//
//        Long update_reading_key = savedReadings.getReadingKey();
//        int expectedScore = 4;
//
//        ReadingGiveScoreDto requestDto = ReadingGiveScoreDto.builder()
//                .score(expectedScore)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/reading/givescore/" + update_reading_key;
//
//        //when
//        HttpEntity<ReadingGiveScoreDto> requestEntity = new HttpEntity<>(requestDto);
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        //then
//        List<Reading> all = readingRepository.findAll();
//        assertThat(all.get(0).getBook().getTitle()).isEqualTo("테스트 책");
//        assertThat(all.get(0).getScore()).isEqualTo(4);
//    }
//
//    @Test
//    public void CalcPage_test() throws Exception { //페이지 이동
//        //given
//        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
//        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);
//
//        Reading savedReadings = readingRepository.save(Reading.builder()
//                .book(test_book)
//                .user(test_user)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build());
//
//        Long update_reading_key = savedReadings.getReadingKey();
//        int expectedPage = 2;
//
//        ReadingCalcCurrentPageDto requestDto = ReadingCalcCurrentPageDto.builder()
//                .option(1)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/reading/calcpage/" + update_reading_key;
//
//        //when
//        HttpEntity<ReadingCalcCurrentPageDto> requestEntity = new HttpEntity<>(requestDto);
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        //then
//        List<Reading> all = readingRepository.findAll();
//        assertThat(all.get(0).getBook().getTitle()).isEqualTo("테스트 책");
//        assertThat(all.get(0).getCurrentPage()).isEqualTo(expectedPage);
//    }
//
//    @Test
//    public void delete_test() throws Exception { //읽고 있는 중 책 삭제
//        //given
//        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
//        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);
//
//        Reading savedReadings = readingRepository.save(Reading.builder()
//                .book(test_book)
//                .user(test_user)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build());
//
//        Long update_reading_key = savedReadings.getReadingKey();
//        String url = "http://localhost:" + port + "/api/v1/reading/" + update_reading_key;
//
//        //when
//        restTemplate.delete(url);
//
//        //경민 테스트용 -> param을 따로 떼어줌!!!
////        url = "http://localhost:"+port+"/api/v1/books/{id}";
////
////        //when
////        Map< String, String > params = new HashMap<String, String >();
////        params.put("id", "0");
////        restTemplate.delete(url, params);
//
//        //then
//        List<Reading> all = readingRepository.findAll();
//        assertThat(all.size()).isEqualTo(0);
//
//    }
//
//    @Test
//    public void findById_test() throws Exception { //id에 따라 reading 조회
//        //given
//        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
//        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);
//
//        Reading savedReadings = readingRepository.save(Reading.builder()
//                .book(test_book)
//                .user(test_user)
//                .currentPage(1)
//                .score(0)
//                .isWrittenBookReport(0)
//                .bookReport("")
//                .build());
//
//        Long update_reading_key = savedReadings.getReadingKey();
//        int expectedPage = 2;
//
//        ReadingCalcCurrentPageDto requestDto = ReadingCalcCurrentPageDto.builder()
//                .option(1)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/reading/calcpage/" + update_reading_key;
//
//        //when
//        HttpEntity<ReadingCalcCurrentPageDto> requestEntity = new HttpEntity<>(requestDto);
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        //then
//        List<Reading> all = readingRepository.findAll();
//        assertThat(all.get(0).getBook().getTitle()).isEqualTo("테스트 책");
//        assertThat(all.get(0).getCurrentPage()).isEqualTo(expectedPage);
//    }
//
//    @Test
//    public void findAll_test() throws Exception { //option과 id에 따라 user나 book 정보를 조회
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
