package com.liverary.book.springboot.web;

import  com.liverary.book.springboot.service.*;
import com.liverary.book.springboot.web.dto.reading.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/api/v1/reading/record") // -> id에 따라 모든 인자값 받아오는 API
//    public void testing() throws Exception {
////        TTSService.synthesizeText("hello world");
////        TTSService.mainob();
//    }
//    @PutMapping("/api/v1/reading/record/{id}")
//    public Long record(@PathVariable Long id, @RequestBody ReadingUpdateRequestDto requestDto) { // 녹음해서 넣는건데.. 아직 안됨
//        return readingService.update(id, requestDto);
//    }
}