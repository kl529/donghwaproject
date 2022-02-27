package com.liverary.book.springboot.web.dto;

import com.liverary.book.springboot.domain.file.Files;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {
    private String origFilename;
    private String filename;
    private String filePath;

    public Files toEntity() {

        Files build = Files.builder()
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileDto( String origFilename, String filename, String filePath) {

        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
