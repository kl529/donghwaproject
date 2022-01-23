package com.liverary.book.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookUpdateRequestDto {
    // 입력시 오타가 나는 가능성을 고려??
    private String title;
    private String author;
    private String publisher;
    private String country;

}
