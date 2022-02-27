package com.liverary.book.springboot.web;

import com.liverary.book.springboot.config.auth.dto.SessionUser;
import com.liverary.book.springboot.domain.book.Book;
import com.liverary.book.springboot.domain.user.User;
import com.liverary.book.springboot.service.BookService;

import com.liverary.book.springboot.service.ReadingService;
import com.liverary.book.springboot.service.UserService;
import com.liverary.book.springboot.web.dto.book.BookIntroDto;
import com.liverary.book.springboot.web.dto.book.BookResponseDto;
import com.liverary.book.springboot.web.dto.book.BookUpdateRequestDto;

import com.liverary.book.springboot.web.dto.reading.ReadingListResponseDto;
import com.liverary.book.springboot.web.dto.reading.ReadingResponseDto;
import com.liverary.book.springboot.web.dto.reading.ReadingSaveRequestDto;
import com.liverary.book.springboot.web.dto.reading.ReadingUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BookService bookService;
    private final ReadingService readingService;
    private final HttpSession httpSession;
    private final UserService userService;

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

         model.addAttribute("books",bookService.findAllDesc());
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

    @GetMapping("/book/content/{bookKey}")
    public String bookContent(@PathVariable Long bookKey, Model model){

        // book-content.mustache를 통해 책 내용을 보이기 위해필요
        BookResponseDto bookDto = bookService.findById(bookKey);
        model.addAttribute("book",bookDto);

        // bookKey를 갖는 책이 reading에 들어있는지 확인 필요
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        String email = sessionUser.getEmail();
        Long userKey = userService.getUserKey(email);
        List<ReadingListResponseDto> reading = readingService.findReadingDesc(userKey, bookKey);
        Long readingKey = null;

        if (reading.size()==1){
            // 이미 reading에 존재하는 경우
            ReadingListResponseDto readingListDto = reading.get(0);
            readingKey = readingListDto.getId();

        }else if(reading.size()>=2){
            // reading에 여러개가 존재하는 경우 -> 발생하면 안됨
            System.out.println("Error : userKey와 bookKey가 모두 일치하는 reading이 2개 이상입니다.");

        }else{
            // reading에 존재하지 않는 경우(해당책을 사용자가 처음 읽기 시작함)
            // 해당 bookKey와 userKey를 갖는 reading 생성 필요 -> startreading
            // Book과 User을 리턴할수 있는 새로운 코드를 작성한다 (V)
            Book currentBook = bookService.findByIdBook(bookKey);
            User currentUser = userService.findByEmailUser(email);

            ReadingSaveRequestDto requestDto = ReadingSaveRequestDto.builder()
                    .book(currentBook)
                    .user(currentUser)
                    .currentPage(1)
                    .score(0)
                    .isWrittenBookReport(0)
                    .bookReport("")
                    .build();
            readingKey = readingService.StartReading(requestDto);
        }

        // 페이지관리
        if(readingKey != null){
            ReadingResponseDto readingDto = readingService.findById(readingKey);
            int currentPage = readingDto.getCurrentpage();
            model.addAttribute("reading", readingDto);
            model.addAttribute("readingKey", readingKey);
        }

        return "book-content";
    }

    @GetMapping("/user/info")
    public String myInfo(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getEmail());
        }

        // readingList를 bookList로 바꾸어 model에 추가
        String email = user.getEmail();
        Long userKey = userService.getUserKey(email);
        List<ReadingListResponseDto> readingList = readingService.findAllDesc(userKey,1);
        List<BookResponseDto> bookList = new ArrayList<>();
        for(int i = 0 ; i <(readingList).size(); i++){
            BookResponseDto dto = new BookResponseDto(readingList.get(i).getBook());
            bookList.add(dto);
        }
        model.addAttribute("reading",bookList);

        return "myinfo";
    }

    @GetMapping("/reading/save_review/{bookKey}")
    public String bookReviewSave(@PathVariable Long bookKey, Model model){
        BookResponseDto dto = bookService.findById(bookKey);

//        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
//        String email = sessionUser.getEmail();
//        User currentUser = userService.findByEmailUser(email);

        model.addAttribute("book",dto);
//
//        // 해당 bookKey와 userKey를 갖는 reading 생성 필요 -> startreading
//        // Book과 User을 리턴할수 있는 새로운 코드를 작성한다 (V)
        //----

//        ReadingListResponseDto rdto = readingService.findReadingDesc(currentUser.getUserKey(), currentBook.getBookKey()).get(0);
//
//        ReadingUpdateRequestDto requestDto = ReadingUpdateRequestDto.builder()
//                .book(currentBook)
//                .user(currentUser)
//                .currentPage(rdto.getCurrentpage()) // 현재 페이지 설정
//                .score(rdto.getScore()) //현재 점수
//                .isWrittenBookReport(1)
//                .bookReport("")
//                .build();
//        readingService.SaveBookReport(rdto.getId(), requestDto);
        return "write-report";
    }

    @GetMapping("/tts")
    public String gettts(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getEmail());
        }

        // readingList를 bookList로 바꾸어 model에 추가
        String email = user.getEmail();
        Long userKey = userService.getUserKey(email);
        List<ReadingListResponseDto> readingList = readingService.findAllDesc(userKey,1);
        List<BookResponseDto> bookList = new ArrayList<>();
        for(int i = 0 ; i <(readingList).size(); i++){
            BookResponseDto dto = new BookResponseDto(readingList.get(i).getBook());
            bookList.add(dto);
        }
        model.addAttribute("reading",bookList);

        return "tts";
    }

}

