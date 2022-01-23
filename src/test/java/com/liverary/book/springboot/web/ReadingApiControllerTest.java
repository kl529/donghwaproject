package com.liverary.book.springboot.web;

import com.liverary.book.springboot.domain.reading.ReadingRepository;
import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.web.dto.reading.ReadingCalcCurrentPageDto;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

// For mockMvc

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadingApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReadingRepository readingRepository;

    @After
    public void tearDown() throws Exception {
        readingRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        //given
        String bookReport = "안녕하세요";
        int score = 3;
        ReadingSaveRequestDto requestDto = ReadingSaveRequestDto.builder()
                .currentPage(141)
                .score(3)
                .isWrittenBookReport(1)
                .bookReport("안녕하세요")
                .build();

        String url = "http://localhost:" + port + "/api/v1/reading";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        //then
        List<Reading> all = readingRepository.findAll();
        assertThat(all.get(0).getScore()).isEqualTo(score);
        assertThat(all.get(0).getBookReport()).isEqualTo(bookReport);
    }

    @Test
    public void Posts_수정된다() throws Exception {
        //given
        Reading savedPosts = readingRepository.save(Reading.builder()
                .currentPage(141)
                .score(3)
                .isWrittenBookReport(1)
                .bookReport("안녕하세요")
                .build());

        Long updateId = savedPosts.getReadingKey();
        String expectedContent = "변경완료";
        int expectedscore = 4;

        ReadingCalcCurrentPageDto requestDto = ReadingCalcCurrentPageDto.builder()
                .option(1)
                .build();

//        readingUpdateRequestDto requestDto = readingUpdateRequestDto.builder()
//                .currentPage(141)
//                .score(expectedscore)
//                .isWrittenBookReport(1)
//                .bookReport(expectedContent)
//                .userKey(100L)
//                .bookKey(10L)
//                .build();

        String url = "http://localhost:" + port + "/api/v1/reading/calcpage/" + updateId;

        //when
        HttpEntity<ReadingCalcCurrentPageDto> requestEntity = new HttpEntity<>(requestDto);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //then
        List<Reading> all = readingRepository.findAll();
        assertThat(all.get(0).getCurrentPage()).isEqualTo(142);
//        assertThat(all.get(0).getScore()).isEqualTo(expectedscore);
    }
}
