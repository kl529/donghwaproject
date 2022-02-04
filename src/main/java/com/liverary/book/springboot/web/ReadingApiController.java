package com.liverary.book.springboot.web;

import  com.liverary.book.springboot.service.*;
import com.liverary.book.springboot.web.dto.reading.*;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Configuration
public class ReadingApiController {

//    @Value("#application-kako['kakako_code']")
//    @Value("${kakako_code}")
    private String api_kakao = "7bd3af3fe5fadac891ef28bc5e0a64aa";

    private final ReadingService readingService;

    @PostMapping("/api/v1/reading/startreading") //StartReading -> 책읽기 시작
    public Long StartReading(@RequestBody ReadingSaveRequestDto requestDto) {
        return readingService.StartReading(requestDto);
    }

    @PutMapping("/api/v1/reading/savebookreport/{id}") //독후감 내용 넣는 API
    public Long SaveBookReport(@PathVariable Long id, @RequestBody ReadingUpdateRequestDto requestDto) {
        return readingService.SaveBookReport(id, requestDto);
    }

    @PutMapping("/api/v1/reading/givescore/{id}") //별점주는 API
    public Long GiveScore(@PathVariable Long id, @RequestBody ReadingUpdateRequestDto requestDto) {
        return readingService.GiveScore(id, requestDto);
    }

    @PutMapping("/api/v1/reading/calcpage/{id}") //페이지 바꾸는 API 1이면 +, 0이면 -임
    public Long CalcPage(@PathVariable Long id, @RequestBody ReadingCalcCurrentPageDto requestDto) {
        return readingService.CalcPage(id, requestDto);
    }

    @PutMapping("/api/v1/reading/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReadingUpdateRequestDto requestDto) {
        return readingService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/reading/{id}")
    public Long delete(@PathVariable Long id) { // 데이터 삭제하는 API
        readingService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/reading/{id}") // -> id에 따라 모든 인자값 받아오는 API
    public ReadingResponseDto findById(@PathVariable Long id) {
        return readingService.findById(id);
    }

    @GetMapping("/api/v1/reading/list/{id}/{option}") // -> option과 id에 따라 bookkey, userkey로 reading table을 읽는 함수
    public List<ReadingListResponseDto> findAll(@PathVariable Long id, int option) {
        return readingService.findAllDesc(id, option);
    }

    @PostMapping("/api/v1/reading/tts") // -> id에 따라 모든 인자값 받아오는 API
    public String synthesize (String input_text) throws Exception {
            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://kakaoi-newtone-openapi.kakao.com/v1/synthesize");
            String result = "";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            headers.set("Transfer-Encoding", "chunked");
            headers.set("X-DSS-Service", "DICTATION");
            headers.set("Authorization", "KakaoAK "+api_kakao);

            String body = "<speak>hello my name is jiwon </speak>"; //xml로 바꾸기 //String 값 받아오기
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            HttpStatus statusCode = responseEntity.getStatusCode();

            result = responseEntity.getBody();
            System.out.println(result);
            return result;
    }
//    @PutMapping("/api/v1/reading/record/{id}")
//    public Long record(@PathVariable Long id, @RequestBody ReadingUpdateRequestDto requestDto) { // 녹음해서 넣는건데.. 아직 안됨
//        return readingService.update(id, requestDto);
//    }
}