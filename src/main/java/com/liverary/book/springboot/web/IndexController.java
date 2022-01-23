package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.dto.BookService;
import com.liverary.book.springboot.web.dto.BookResponseDto;
import com.liverary.book.springboot.web.dto.BookUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BookService bookService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("books",bookService.findAllDesc());
        return "index";
    }
    @GetMapping("/books/update/{id}")
    public String bookUpdate(@PathVariable Long id, Model model ){
        BookResponseDto bookResponseDto = bookService.findById(id);
        model.addAttribute("book", bookResponseDto);
        return "books-update";
    }
}
