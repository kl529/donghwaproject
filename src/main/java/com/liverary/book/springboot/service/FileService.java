package com.liverary.book.springboot.service;

import com.liverary.book.springboot.domain.file.FileRepository;
import com.liverary.book.springboot.domain.file.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    @Autowired
    FileRepository fileRepository;

    public void save(Files files){
        Files f = new Files();
        f.setFilename(files.getFilename());
        f.setFileOriName(files.getFileOriName());
        f.setFileurl(files.getFileurl());
        fileRepository.save(f);
    }
    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("C:\\" + file.getOriginalFilename()));
    }
    public Files findByFno (int id ){
        return fileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id = " + id));
    }

}
