package com.liverary.book.springboot.service;

import com.liverary.book.springboot.domain.reading.Reading;
import com.liverary.book.springboot.domain.reading.ReadingRepository;
import com.liverary.book.springboot.web.dto.reading.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("ReadingService")
public class ReadingService {
    private final ReadingRepository readingRepository;

    @Transactional
    public Long StartReading(ReadingSaveRequestDto requestDto) { //StartReading
        return readingRepository.save(requestDto.toEntity()).getReadingKey();
    }

    @Transactional
    public Long SaveBookReport(Long id, ReadingUpdateRequestDto requestDto) {
        List<Reading> lists = readingRepository.findAllDescbyBook(id);
        if (lists.size() == 0 || lists.get(id.intValue()-1).getClass().getName() != "java.lang.Long"){
            new IllegalArgumentException("해당 책이 없습니다. id=" + id);
        }
        Reading reading = lists.get(id.intValue()-1);
        String input = requestDto.getBookReport();
        try{
            if(!input.equals("")){
                reading.bookReportUpdate(input);
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
    public Long GiveScore(Long id, ReadingUpdateRequestDto requestDto) {
        Reading reading = readingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        int score = requestDto.getScore();
        try{
            if(score >= 1 || score <= 5){
                reading.scoreUpdate(score);
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
    public Long CalcPage(Long id, ReadingCalcCurrentPageDto requestDto) {
        Reading reading = readingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reading.pageUpdate(requestDto.getOption());

        return id;
    }

    @Transactional
    public Long update(Long id, ReadingUpdateRequestDto requestDto) {
        Reading reading = readingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reading.update( requestDto.getBook(), requestDto.getUser(), requestDto.getCurrentPage(), requestDto.getScore(), requestDto.getIsWrittenBookReport(), requestDto.getBookReport());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Reading reading = readingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        readingRepository.delete(reading);
    }

    @Transactional(readOnly = true)
    public ReadingResponseDto findById(Long id) { //이걸로 모든 GET~~~를 대체해버리자
        Reading entity = readingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new ReadingResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ReadingListResponseDto> findAllDesc(Long id, int option) {
        if(option == 0){ //option이 0 일때 --> Bookkey로 검색
            return readingRepository.findAllDescbyBook(id).stream()
                    .map(ReadingListResponseDto::new)
                    .collect(Collectors.toList());
        }
        else{ //option이 1일때 --> Userkey로 검색
            return readingRepository.findAllDescbyUser(id).stream()
                    .map(ReadingListResponseDto::new)
                    .collect(Collectors.toList());
        }
//        return readingRepository.findAllDesc().stream()
//                .map(readingListResponseDto::new)
//                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReadingListResponseDto> findReadingDesc(Long user_id, Long book_id) {
            return readingRepository.findAllDescbyBook_User(user_id,book_id).stream()
                    .map(ReadingListResponseDto::new)
                    .collect(Collectors.toList());
    }
}
