package com.liverary.book.springboot.web;

import  com.liverary.book.springboot.service.*;
import com.liverary.book.springboot.web.dto.*;
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
public class ReadingsApiController {

    private final ReadingsService ReadingsService;

    @PostMapping("/api/v1/readings/startreadings") //StartReading -> 책읽기 시작
    public Long StartReading(@RequestBody ReadingsSaveRequestDto requestDto) {
        return ReadingsService.StartReading(requestDto);
    }

    @PutMapping("/api/v1/readings/savebookreport/{id}") //독후감 내용 넣는 API
    public Long SaveBookReport(@PathVariable Long id, @RequestBody ReadingsUpdateRequestDto requestDto) {
        return ReadingsService.SaveBookReport(id, requestDto);
    }

    @PutMapping("/api/v1/readings/givescore/{id}") //별점주는 API
    public Long GiveScore(@PathVariable Long id, @RequestBody ReadingsUpdateRequestDto requestDto) {
        return ReadingsService.GiveScore(id, requestDto);
    }

    @PutMapping("/api/v1/readings/calcpage/{id}") //페이지 바꾸는 API 1이면 +, 0이면 -임
    public Long CalcPage(@PathVariable Long id, @RequestBody ReadingsCalcCurrentPageDto requestDto) {
        return ReadingsService.CalcPage(id, requestDto);
    }

    @PutMapping("/api/v1/readings/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReadingsUpdateRequestDto requestDto) {
        return ReadingsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/readings/{id}")
    public Long delete(@PathVariable Long id) { // 데이터 삭제하는 API
        ReadingsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/readings/{id}") // -> id에 따라 모든 인자값 받아오는 API
    public ReadingsResponseDto findById(@PathVariable Long id) {
        return ReadingsService.findById(id);
    }

    @GetMapping("/api/v1/readings/list/{id}/{option}") // -> option과 id에 따라 bookkey, userkey로 readings table을 읽는 함수
    public List<ReadingsListResponseDto> findAll(@PathVariable Long id, int option) {
        return ReadingsService.findAllDesc(id, option);
    }

    @GetMapping("/api/v1/readings/record") // -> id에 따라 모든 인자값 받아오는 API
    public void testing() throws Exception {
        TTSService.synthesizeText("hello world");
//        TTSService.mainob();
    }
    @PutMapping("/api/v1/readings/record/{id}")
    public Long record(@PathVariable Long id, @RequestBody ReadingsUpdateRequestDto requestDto) { // 녹음해서 넣는건데.. 아직 안됨
        return ReadingsService.update(id, requestDto);
    }
}