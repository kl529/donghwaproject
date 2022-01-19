package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.book.dto.BookService;
import com.liverary.book.springboot.web.dto.BookResponseDto;
import com.liverary.book.springboot.web.dto.BookSaveRequestDto;
import com.liverary.book.springboot.web.dto.BookUpdateRequestDto;
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

    // 책수정이 필요하려나
//    @PutMapping("/api/v1/books/admin/{id}")
//    public Long update(@PathVariable Long id, @RequestBody BookUpdateRequestDto requestDto){
//        return BookService.update(id, requestDto);
//    }

    @GetMapping("/api/v1/books/{id}")
    public BookResponseDto findById(@PathVariable Long id ){
        return bookService.findById(id);
    }
}
