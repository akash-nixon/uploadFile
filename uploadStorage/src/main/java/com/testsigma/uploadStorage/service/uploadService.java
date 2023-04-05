package com.testsigma.uploadStorage.service;

import com.testsigma.uploadStorage.entity.FileData;
import com.testsigma.uploadStorage.repository.uploadRepository;
import com.testsigma.uploadStorage.repository.fileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class uploadService {
    @Autowired
    private uploadRepository repository;

    @Autowired
    private fileRepository fileRepository;

    private final String path = "/home/akash/UploadedFiles/";


    public String uploadFileToFileSystem(MultipartFile file) throws IOException {
        String filePAth = path+file.getOriginalFilename();
        FileData fileData = fileRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePAth).build());

        file.transferTo(new File(filePAth));

        if(fileData!=null){
            return "File uploaded:"+filePAth;
        }
        return null;
    }

    public byte[] downloadFileFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileRepository.findByName(fileName);
        String filepath = fileData.get().getFilePath();
        byte [] images = Files.readAllBytes(new File(filepath).toPath());
        return images;
    }

    public String deleteFile(Long id){
        Optional<FileData> fileData = fileRepository.findById(id);
        fileRepository.deleteById(id);
        return "File deleted: "+fileData.get().getFilePath();
    }
    public List<FileData> list(){
        List<FileData> fileData = fileRepository.findAll();
        return fileData;
    }
}
