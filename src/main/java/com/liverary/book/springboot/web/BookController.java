package com.liverary.book.springboot.web;

import com.liverary.book.springboot.service.BookService;
import com.liverary.book.springboot.service.FileService;
import com.liverary.book.springboot.util.MD5Generator;
import com.liverary.book.springboot.web.dto.FileDto;
import com.liverary.book.springboot.web.dto.book.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final FileService fileService;
    private final BookService bookService;

    @PostMapping("/posts")
    public String write(@RequestParam("file") MultipartFile files, BookSaveRequestDto bookSaveRequestDto) {
        System.out.println("Post Mapping");
        System.out.println(bookSaveRequestDto.getAuthor());
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);
            System.out.println(fileDto.toString());

            Long fileId = fileService.saveFile(fileDto);
            bookSaveRequestDto.setFileId(fileId);
            bookService.save(bookSaveRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/homepage";
    }
}
