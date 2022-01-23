package com.liverary.book.springboot.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/homepage")
    public String homepage(){
        return "home";
    }

    @GetMapping("/book/save")
    public String bookSave(){
        return "book-save";
    }

    @GetMapping("/book/delete")
    public String bookDelete(){
        return "book-delete";
    }
}

