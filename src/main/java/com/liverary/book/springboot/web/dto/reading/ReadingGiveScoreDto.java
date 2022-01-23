package com.liverary.book.springboot.web.dto.reading;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingGiveScoreDto {
    private int score;

    @Builder
    public ReadingGiveScoreDto(int score) {
        this.score = score;
    }
}
