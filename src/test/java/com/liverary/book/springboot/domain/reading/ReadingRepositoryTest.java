package com.liverary.book.springboot.domain.reading;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.user.Role;
import com.liverary.book.springboot.domain.user.User;
import com.liverary.book.springboot.web.dto.reading.ReadingSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadingRepositoryTest {

    @Autowired
    ReadingRepository readingRepository;

//    @After
//    public void cleanup() {
//        readingRepository.deleteAll();
//    }

    @Test
    public void 게시글저장_불러오기() {
        //given

        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);

        readingRepository.save(Reading.builder()
                .book(test_book)
                .user(test_user)
                .currentPage(1)
                .score(0)
                .isWrittenBookReport(0)
                .bookReport("")
                .build());

        Book test_book2 = new Book("테스트 책2", "김지원2", "우리집2", "한국2", "재밋음2", "북커버2", "책내용2", 600, new Date(2022-01-13));
        User test_user2 = new User("jiwon803@gmail.com22", Role.ADMIN);

        readingRepository.save(Reading.builder()
                .book(test_book2)
                .user(test_user2)
                .currentPage(1)
                .score(0)
                .isWrittenBookReport(0)
                .bookReport("")
                .build());

        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);

        ReadingSaveRequestDto requestDto = ReadingSaveRequestDto.builder()
                .book(test_book)
                .user(test_user)
                .currentPage(1)
                .score(0)
                .isWrittenBookReport(0)
                .bookReport("")
                .build();

        String url = "http://localhost:" + 8080 + "/api/v1/reading/startreading";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //then
        List<Reading> all = readingRepository.findAll();
        assertThat(all.get(0).getBook().getTitle()).isEqualTo(test_book.getTitle());
        assertThat(all.get(0).getUser().getEmail()).isEqualTo(test_user.getEmail());
        assertThat(all.get(0).getCurrentPage()).isEqualTo(1);
        assertThat(all.get(0).getScore()).isEqualTo(0);
        assertThat(all.get(0).getIsWrittenBookReport()).isEqualTo(0);
        assertThat(all.get(0).getBookReport()).isEqualTo("");

        //when
        List<Reading> readingList = readingRepository.findAllDescbyBook(1L);

        //then
        Reading reading = readingList.get(0);

        assertThat(reading.getCurrentPage()).isEqualTo(1);
        assertThat(reading.getScore()).isEqualTo(0);
        assertThat(reading.getIsWrittenBookReport()).isEqualTo(0);
        assertThat(reading.getBookReport()).isEqualTo("");
    }
}