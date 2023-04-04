package com.testsigma.uploadStorage.repository;

import com.testsigma.uploadStorage.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface uploadRepository extends JpaRepository<ImageData,Long> {

    Optional<ImageData> findByName(String fileName);

}
