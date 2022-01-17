package com.liverary.book.springboot.web.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingsGiveScoreDto {
    private int score;

    @Builder
    public ReadingsGiveScoreDto(int score) {
        this.score = score;
    }
}
