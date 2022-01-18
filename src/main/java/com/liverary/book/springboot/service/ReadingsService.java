package com.liverary.book.springboot.service;

import com.liverary.book.springboot.domain.reading.Readings;
import com.liverary.book.springboot.domain.reading.ReadingsRepository;
import com.liverary.book.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReadingsService {
    private final ReadingsRepository ReadingsRepository;

    @Transactional
    public Long StartReading(ReadingsSaveRequestDto requestDto) { //StartReading
        return ReadingsRepository.save(requestDto.toEntity()).getID();
    }

    @Transactional
    public Long SaveBookReport(Long id, ReadingsUpdateRequestDto requestDto) {
        Readings Readings = ReadingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        String input = requestDto.getBookReport();
        try{
            if(!input.equals("")){
                Readings.bookReportUpdate(input);
            }
            else{
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("빈칸을 입력했습니다.");
        }
        return id;
    }

    @Transactional
    public Long GiveScore(Long id, ReadingsUpdateRequestDto requestDto) {
        Readings Readings = ReadingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        int score = requestDto.getScore();
        try{
            if(score >= 1 || score <= 5){
                Readings.scoreUpdate(score);
            }
            else{
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("1~5사이의 수를 입력해주세요");
        }

        return id;
    }

    @Transactional
    public Long CalcPage(Long id, ReadingsCalcCurrentPageDto requestDto) {
        Readings Readings = ReadingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        Readings.pageUpdate(requestDto.getOption());

        return id;
    }

    @Transactional
    public Long update(Long id, ReadingsUpdateRequestDto requestDto) {
        Readings Readings = ReadingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        Readings.update(requestDto.getCurrentPage(), requestDto.getScore(), requestDto.getIsWrittenBookReport(), requestDto.getBookReport(), requestDto.getUserKey(), requestDto.getBookKey());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Readings Readings = ReadingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        ReadingsRepository.delete(Readings);
    }

    @Transactional(readOnly = true)
    public ReadingsResponseDto findById(Long id) { //이걸로 모든 GET~~~를 대체해버리자
        Readings entity = ReadingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new ReadingsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ReadingsListResponseDto> findAllDesc(Long id, int option) {
        if(option == 0){ //option이 0 일때 --> Bookkey로 검색
            return ReadingsRepository.findAllDescbyBook(id).stream()
                    .map(ReadingsListResponseDto::new)
                    .collect(Collectors.toList());
        }
        else{ //option이 1일때 --> Userkey로 검색
            return ReadingsRepository.findAllDescbyUser(id).stream()
                    .map(ReadingsListResponseDto::new)
                    .collect(Collectors.toList());
        }
//        return ReadingsRepository.findAllDesc().stream()
//                .map(ReadingsListResponseDto::new)
//                .collect(Collectors.toList());
    }
}
