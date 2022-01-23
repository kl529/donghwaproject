package com.liverary.book.springboot.domain.posts;

import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.reading.ReadingRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        readingRepository.save(Reading.builder()
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
    }
}