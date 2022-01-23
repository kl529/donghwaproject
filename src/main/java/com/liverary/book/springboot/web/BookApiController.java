package com.liverary.book.springboot.web;

<<<<<<< HEAD
import com.liverary.book.springboot.service.BookService;
import com.liverary.book.springboot.web.dto.book.BookIntroDto;
import com.liverary.book.springboot.web.dto.book.BookResponseDto;
import com.liverary.book.springboot.web.dto.book.BookSaveRequestDto;
import com.liverary.book.springboot.web.dto.book.BookUpdateRequestDto;
=======
import com.liverary.book.springboot.service.book.dto.BookService;
import com.liverary.book.springboot.web.dto.BookResponseDto;
import com.liverary.book.springboot.web.dto.BookSaveRequestDto;
>>>>>>> origin/ykm
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.liverary.book.springboot.service.BookService;
import com.liverary.book.springboot.web.dto.book.BookResponseDto;
import com.liverary.book.springboot.web.dto.book.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

<<<<<<< HEAD
    @GetMapping("/api/v1/books/search/{search}")
    public List<BookIntroDto> findBySearch(@PathVariable String search){
        return bookService.findBySearch(search);
    }

    @GetMapping("/api/v1/books/list")
    public List <BookIntroDto> findAllDesc(){
        return bookService.findAllDesc();
    }


=======
    @DeleteMapping("/api/v1/books/{id}")
    public Long delete(@PathVariable Long id){
        bookService.delete(id);
        return id;
    }
>>>>>>> origin/ykm
}
