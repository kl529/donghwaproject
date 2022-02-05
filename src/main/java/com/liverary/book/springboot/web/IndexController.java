package com.liverary.book.springboot.web;

import com.liverary.book.springboot.config.auth.dto.SessionUser;
import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.service.BookService;
<<<<<<< HEAD
import com.liverary.book.springboot.web.dto.book.BookIntroDto;
import com.liverary.book.springboot.web.dto.book.BookResponseDto;
import com.liverary.book.springboot.web.dto.book.BookUpdateRequestDto;
=======
import com.liverary.book.springboot.web.dto.book.BookResponseDto;
>>>>>>> origin/ykm
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======

import javax.servlet.http.HttpSession;
>>>>>>> origin/ykm

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BookService bookService;
<<<<<<< HEAD

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("books",bookService.findAllDesc());
        return "index";
    }
    @GetMapping("/books/save")
        public String booksSave(){
            return "book-save";
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
    @GetMapping("/api/v1/books/info/{id}")
    public String bookDetail(@PathVariable Long id, Model model ){
        BookResponseDto bookResponseDto = bookService.findById(id);
        model.addAttribute("book", bookResponseDto);
        return "book-detail";
    }
=======
    private final HttpSession httpSession;

    @GetMapping("/")
    public String welcome(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getEmail());
        }
        return "welcome";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getEmail());
        }

        // model.addAttribute("books",bookService.findAllDesc());
        return "home";
    }

    @GetMapping("/book/save")
    public String bookSave(){
        return "book-save";
    }

    @GetMapping("/book/delete")
    public String bookDelete(){
        return "book-delete";
    }

    @GetMapping("/book/info/{bookKey}")
    public String bookInfo(@PathVariable Long bookKey, Model model){
        BookResponseDto dto = bookService.findById(bookKey);
        model.addAttribute("book",dto);

        return "book-info";
    }

    @GetMapping("/user/info")
    public String myInfo(){
        return "myinfo";
    }

>>>>>>> origin/ykm
}

