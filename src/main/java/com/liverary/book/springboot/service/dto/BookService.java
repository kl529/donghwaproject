package com.liverary.book.springboot.service.dto;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import com.liverary.book.springboot.web.dto.BookIntroDto;
import com.liverary.book.springboot.web.dto.BookResponseDto;
import com.liverary.book.springboot.web.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Long save (BookSaveRequestDto bookSaveRequestDto){
        return bookRepository.save(bookSaveRequestDto.toEntity()).getBookKey();
    }
    public BookResponseDto findById(Long id){
        Book entity = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id = " + id));
        return new BookResponseDto(entity);
    }
    @Transactional(readOnly = true)
    public List<BookIntroDto> findAllDesc(){
        return bookRepository.findAllDesc().stream().map(BookIntroDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List <BookIntroDto> findBySearch(String str ){
        return bookRepository.findBySearch(str).stream().map(BookIntroDto::new).collect(Collectors.toList());
    }

}
