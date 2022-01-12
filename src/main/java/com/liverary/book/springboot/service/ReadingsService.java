package com.liverary.book.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReadingsService {
    private final ReadingsRepository ReadingsRepository;

    @Transactional
    public Long save(ReadingsSaveRequestDto requestDto) {
        return ReadingsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ReadingsUpdateRequestDto requestDto) {
        Readings Readings = ReadingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        Readings.update(requestDto.getTitle(), requestDto.getContent());

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
    public List<ReadingsListResponseDto> findAllDesc() {
        return ReadingsRepository.findAllDesc().stream()
                .map(ReadingsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
