package com.liverary.book.springboot.web;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.reading.ReadingRepository;
import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.user.Role;
import com.liverary.book.springboot.domain.user.User;
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

import java.sql.Date;
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
    public void StartReading_test() throws Exception { // 책 읽기 시작
        //given
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

        String url = "http://localhost:" + port + "/api/v1/reading/startreading";

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
    }

    @Test
    public void SaveBookReport_test() throws Exception { //BookReport 수정
    }

    @Test
    public void GiveScore_test() throws Exception { //점수 수정
    }

    @Test
    public void CalcPage_test() throws Exception { //페이지 이동
    }

    @Test
    public void delete_test() throws Exception { //읽고 있는 중 책 삭제
    }

    @Test
    public void findById_test() throws Exception { //id에 따라 reading 조회
    }

    @Test
    public void findAll_test() throws Exception { //option과 id에 따라 user나 book 정보를 조회
    }

    //--------------
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
