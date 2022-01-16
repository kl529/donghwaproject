package com.liverary.book.springboot.web.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;
        HelloResponseDto helloResponseDto = new HelloResponseDto(name,amount);


        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);


    }
}
