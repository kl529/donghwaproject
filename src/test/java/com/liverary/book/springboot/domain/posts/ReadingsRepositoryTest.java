package com.liverary.book.springboot.domain.posts;

import com.liverary.book.springboot.domain.reading.Readings;
import com.liverary.book.springboot.domain.reading.ReadingsRepository;
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
public class ReadingsRepositoryTest {

    @Autowired
    ReadingsRepository readingsRepository;

    @After
    public void cleanup() {
        readingsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given

        readingsRepository.save(Readings.builder()
                .currentPage(141)
                .score(3)
                .isWrittenBookReport(1)
                .bookReport("안녕하세요")
                .userKey(100L)
                .bookKey(10L)
                .build());

        //when
        List<Readings> readingsList = readingsRepository.findAll();

        //then
        Readings readings = readingsList.get(0);
        assertThat(readings.getCurrentPage()).isEqualTo(141);
        assertThat(readings.getScore()).isEqualTo(3);
    }
}