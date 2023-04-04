package com.testsigma.uploadStorage.repository;

import com.testsigma.uploadStorage.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface fileRepository extends JpaRepository<FileData,Long> {
    Optional<FileData> findByName(String fileName);
}
