package com.limp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/31 17:52
 * @website： www.shinians.com
 */
@Service
public class UploadService {
    Logger logger = LoggerFactory.getLogger(UploadService.class);
    private final Path rootLocation;

    @Autowired
    public UploadService() {
        this.rootLocation = Paths.get("D:/");
    }


    /**
     * 存储文件
     * @param file
     * @return
     */
    public boolean storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                logger.error("file is not null");
                return false;
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            logger.error("file upload error");
            return false;
        }
        return true;
    }
}
