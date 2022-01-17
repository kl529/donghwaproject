package com.liverary.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadingsCalcCurrentPageDto {
    private int option;

    @Builder
    public ReadingsCalcCurrentPageDto(int option) {
        this.option = option;
    }
}
