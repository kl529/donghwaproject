package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.book.dto.BookService;
import com.liverary.book.springboot.web.dto.BookResponseDto;
import com.liverary.book.springboot.web.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BookService bookService;

    @PostMapping("/api/v1/books/admin")
    public Long save(@RequestBody BookSaveRequestDto requestDto){
        return bookService.save(requestDto);
    }

    @GetMapping("/api/v1/books/{id}")
    public BookResponseDto findById(@PathVariable Long id ){
        return bookService.findById(id);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public Long delete(@PathVariable Long id){
        bookService.delete(id);
        return id;
    }
}
