package com.liverary.book.springboot.web;

import  com.liverary.book.springboot.service.*;
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

    @PostMapping("/api/v1/readings")
    public Long save(@RequestBody ReadingsSaveRequestDto requestDto) {
        return ReadingsService.save(requestDto);
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

    @GetMapping("/api/v1/readings/list")
    public List<ReadingsListResponseDto> findAll() {
        return ReadingsService.findAllDesc();
    }
}