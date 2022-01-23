package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.BookService;
import com.liverary.book.springboot.web.dto.book.BookIntroDto;
import com.liverary.book.springboot.web.dto.book.BookResponseDto;
import com.liverary.book.springboot.web.dto.book.BookUpdateRequestDto;
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
        return "welcome";
    }
    @GetMapping("/books/update/{id}")
    public String bookUpdate(@PathVariable Long id, Model model ){
        BookResponseDto bookResponseDto = bookService.findById(id);
        model.addAttribute("book", bookResponseDto);
        return "books-update";
    }
    @GetMapping("/api/v1/books/search/{search}")
    public String bookSearch(@PathVariable String search,Model model ){
        model.addAttribute("books", bookService.findBySearch(search));
        return "search";
    }
}

