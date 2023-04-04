package com.testsigma.uploadStorage.controller;

import com.testsigma.uploadStorage.service.uploadService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin("http://localhost:3000/")
public class uploadController {
    @Autowired
    public uploadService service;

    @PostMapping("/filesys")
    public ResponseEntity<?> uploadFileToFileSystem(@RequestParam("file")MultipartFile file) throws IOException {
        String upload =service.uploadFileToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK).body(upload);
    }

    @GetMapping("/filesys/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData=service.downloadFileFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
}

