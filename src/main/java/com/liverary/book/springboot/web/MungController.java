package com.liverary.book.springboot.web;

import com.liverary.book.springboot.domain.file.Files;
import com.liverary.book.springboot.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class MungController {

    @Autowired
    FileService fileService;
    @RequestMapping("mung/insert")
    public String Insert(){
        return "insert";
    }
    @RequestMapping("mung/fileinsert")
    public String fileinsert(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception{
        Files file = new Files();

        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:/Users/diddm/IdeaProjects/donghwaproject/src/main/resources/static/images/";
        // mung-1은 자기 프로젝트이름으로 체인지!!

        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileurl(fileUrl);
        fileService.save(file);
        return "/home";
    }

    @RequestMapping("mung/index5")
    public String index5(Model model) {
        model.addAttribute("file",fileService.findByFno(1) );
        return "showPicture";
    }

}
