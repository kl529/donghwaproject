package com.liverary.book.springboot.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/homepage")
    public String homepage(){
        return "home";
    }
}

