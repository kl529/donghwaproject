package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.BookService;
import com.liverary.book.springboot.web.dto.book.BookIntroDto;
import com.liverary.book.springboot.web.dto.book.BookResponseDto;
import com.liverary.book.springboot.web.dto.book.BookSaveRequestDto;
import com.liverary.book.springboot.web.dto.book.BookUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
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

    @PutMapping("/api/v1/books/{id}")
    public Long update(@PathVariable Long id , @RequestBody BookUpdateRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }

    @GetMapping("/api/v1/books/list")
    public List <BookIntroDto> findAllDesc(){
        return bookService.findAllDesc();
    }


  //  @GetMapping("/api/v1/books/search/{search}")
   // public List<BookIntroDto> findBySearch(@PathVariable String search){
    //    return bookService.findBySearch(search);
   // }

    @DeleteMapping("/api/v1/books/{id}")
    public Long delete(@PathVariable Long id){
        bookService.delete(id);
        return id;
    }

}
