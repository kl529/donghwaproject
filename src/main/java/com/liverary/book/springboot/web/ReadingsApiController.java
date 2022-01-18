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
    public Long delete(@PathVariable Long id) {
        ReadingsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/readings/{id}")
    public ReadingsResponseDto findById(@PathVariable Long id) {
        return ReadingsService.findById(id);
    }

    @GetMapping("/api/v1/readings/list/{id}/{option}")
    public List<ReadingsListResponseDto> findAll(@PathVariable Long id, int option) {
        return ReadingsService.findAllDesc(id, option);
    }
}