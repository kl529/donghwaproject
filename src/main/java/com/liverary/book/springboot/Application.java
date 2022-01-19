package com.liverary.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//yes
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// 메인클래스
// 자동설정, 스프링 빈 읽기와 생성 모두 자동 생성
// 이 위치부터 설정을 읽어간다.
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
