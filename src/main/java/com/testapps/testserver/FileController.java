package com.testapps.testserver;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @PostMapping( value = "/getFileInfo",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FileInfo multiPartFetcher(MultipartFile file) throws IOException {
        FileInfo fileInfo = FileInfo.builder().build();
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setFileLength(file.getSize());
        fileInfo.setIsFileEmpty(file.isEmpty());
        fileInfo.setFileData(file.getBytes());
        fileInfo.setContentType(file.getContentType());
        fileInfo.setReadable(file.getResource().isReadable());
        return fileInfo;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadToFileSystem(MultipartFile file) throws IOException {
        try {
            file.transferTo(new File("/Users/abi/" + file.getName()));
        } catch (IOException e){
            return ResponseEntity.internalServerError().body("Failed!");
        }
        return ResponseEntity.ok().body("Success!");
    }
}
