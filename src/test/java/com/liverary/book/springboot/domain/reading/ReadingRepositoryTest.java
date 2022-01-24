package com.liverary.book.springboot.domain.reading;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.user.Role;
import com.liverary.book.springboot.domain.user.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadingRepositoryTest {

    @Autowired
    ReadingRepository readingRepository;

    @After
    public void cleanup() {
        readingRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given

        Book test_book = new Book("테스트 책", "김지원", "우리집", "한국", "재밋음", "북커버", "책내용", 500, new Date(2022-01-13));
        User test_user = new User("jiwon803@gmail.com", Role.ADMIN);

        readingRepository.save(Reading.builder()
                .book(test_book)
                .user(test_user)
                .currentPage(141)
                .score(3)
                .isWrittenBookReport(1)
                .bookReport("안녕하세요")
                .build());

        //when
        List<Reading> readingList = readingRepository.findAll();

        //then
        Reading reading = readingList.get(0);

        assertThat(reading.getCurrentPage()).isEqualTo(141);
        assertThat(reading.getScore()).isEqualTo(3);
        assertThat(reading.getIsWrittenBookReport()).isEqualTo(1);
        assertThat(reading.getBookReport()).isEqualTo("안녕하세요");
    }
}