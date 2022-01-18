package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.dto.BookService;
import com.liverary.book.springboot.web.dto.BookIntroDto;
import com.liverary.book.springboot.web.dto.BookResponseDto;
import com.liverary.book.springboot.web.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookApiController {
    private final BookService bookService;
    // 새로운 책 저장
    @PostMapping("/api/v1/books")
    public Long save(@RequestBody BookSaveRequestDto requestDto){
        return bookService.save(requestDto);
    }

    @GetMapping("/api/v1/books/{id}")
    public BookResponseDto findById(@PathVariable Long id ){
        return bookService.findById(id);
    }


    @GetMapping("/api/v1/books/search/{search}")
    public List<BookIntroDto> findBySearch(@PathVariable String search){
        return bookService.findBySearch(search);
    }
    @GetMapping("/api/v1/books/list")
    public List <BookIntroDto> findAllDesc(){
        return bookService.findAllDesc();
    }


}
