package com.malina_ink.resaleplatform.service;

import com.malina_ink.resaleplatform.exception.PhotoUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadUserImage(MultipartFile file, Integer userId) throws PhotoUploadException;

    String uploadAdImage(MultipartFile file, Integer adId) throws PhotoUploadException;

    void deleteImage(String path);
}