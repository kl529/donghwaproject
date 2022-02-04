package com.liverary.book.springboot.web;

import  com.liverary.book.springboot.service.*;
import com.liverary.book.springboot.web.dto.reading.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReadingApiController {

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
    public String synthesize (HttpServletResponse request, MultipartFile speak) throws Exception {
            System.out.println(speak);
            String myPath = request.getRealPath("/adminImg")+"/speak.wav";
            FileUtilCollection.saveImage(speak, myPath);

            String path = request.getRealPath("/adminImg")+"/heykakao.wav";
//		FileUtilCollection.saveImage(speak, path);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

            //add file
            LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
            params.add("file", new FileSystemResource(path));

            //add array
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://kakaoi-newtone-openapi.kakao.com/v1/recognize");
            //another staff
            String result = "";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            headers.set("Content-Type", "application/xml");
            headers.set("Transfer-Encoding", "chunked");
            headers.set("X-DSS-Service", "DICTATION");
            headers.set("Authorization", "KakaoAK "+kakako_code);

            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                    new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers);

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