package com.liverary.book.springboot.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class welcomeController {
    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }
}
