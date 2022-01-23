package com.liverary.book.springboot.service.book.dto;

import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.book.BookRepository;
import com.liverary.book.springboot.web.dto.BookListResponseDto;
import com.liverary.book.springboot.web.dto.BookResponseDto;
import com.liverary.book.springboot.web.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

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

    @Transactional
    public void delete(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id=" + id));

        bookRepository.delete(book);
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDto> findAllDesc(){
        return bookRepository.findAllDesc().stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }
}
