package com.liverary.book.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverary.book.springboot.domain.reading.Readings;
import com.liverary.book.springboot.domain.reading.ReadingsRepository;
import com.liverary.book.springboot.web.dto.ReadingsCalcCurrentPageDto;
import com.liverary.book.springboot.web.dto.ReadingsSaveRequestDto;
import com.liverary.book.springboot.web.dto.ReadingsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// For mockMvc

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadingsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReadingsRepository readingsRepository;

    @After
    public void tearDown() throws Exception {
        readingsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        //given
        String bookReport = "안녕하세요";
        int score = 3;
        ReadingsSaveRequestDto requestDto = ReadingsSaveRequestDto.builder()
                .currentPage(141)
                .score(3)
                .isWrittenBookReport(1)
                .bookReport("안녕하세요")
                .userKey(100L)
                .bookKey(10L)
                .build();

        String url = "http://localhost:" + port + "/api/v1/readings";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        //then
        List<Readings> all = readingsRepository.findAll();
        assertThat(all.get(0).getScore()).isEqualTo(score);
        assertThat(all.get(0).getBookReport()).isEqualTo(bookReport);
    }

    @Test
    public void Posts_수정된다() throws Exception {
        //given
        Readings savedPosts = readingsRepository.save(Readings.builder()
                .currentPage(141)
                .score(3)
                .isWrittenBookReport(1)
                .bookReport("안녕하세요")
                .userKey(100L)
                .bookKey(10L)
                .build());

        Long updateId = savedPosts.getID();
        String expectedContent = "변경완료";
        int expectedscore = 4;

        ReadingsCalcCurrentPageDto requestDto = ReadingsCalcCurrentPageDto.builder()
                .option(1)
                .build();

//        ReadingsUpdateRequestDto requestDto = ReadingsUpdateRequestDto.builder()
//                .currentPage(141)
//                .score(expectedscore)
//                .isWrittenBookReport(1)
//                .bookReport(expectedContent)
//                .userKey(100L)
//                .bookKey(10L)
//                .build();

        String url = "http://localhost:" + port + "/api/v1/readings/calcpage/" + updateId;

        //when
        HttpEntity<ReadingsCalcCurrentPageDto> requestEntity = new HttpEntity<>(requestDto);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //then
        List<Readings> all = readingsRepository.findAll();
        assertThat(all.get(0).getCurrentPage()).isEqualTo(142);
//        assertThat(all.get(0).getScore()).isEqualTo(expectedscore);
    }
}
