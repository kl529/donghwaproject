package com.liverary.book.springboot.web;

<<<<<<< HEAD
import com.liverary.book.springboot.service.BookService;
import com.liverary.book.springboot.web.dto.book.BookIntroDto;
import com.liverary.book.springboot.web.dto.book.BookResponseDto;
import com.liverary.book.springboot.web.dto.book.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookApiController {
    private final BookService bookService;
    // 새로운 책 저장
    @PostMapping("/api/v1/books")
=======
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
>>>>>>> origin/ykm
    public Long save(@RequestBody BookSaveRequestDto requestDto){
        return bookService.save(requestDto);
    }

<<<<<<< HEAD
=======
    // 책수정이 필요하려나
//    @PutMapping("/api/v1/books/admin/{id}")
//    public Long update(@PathVariable Long id, @RequestBody BookUpdateRequestDto requestDto){
//        return BookService.update(id, requestDto);
//    }

>>>>>>> origin/ykm
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
>>>>>>> origin/ykm
}
