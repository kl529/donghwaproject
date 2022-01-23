package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final BookService bookService;
}
