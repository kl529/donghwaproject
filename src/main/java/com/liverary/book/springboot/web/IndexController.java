package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.dto.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final BookService bookService;

    @GetMapping("/")
    public String index (Model model ){
        model.addAttribute("books", bookService.findAllDesc());
        return "index";
    }
}
