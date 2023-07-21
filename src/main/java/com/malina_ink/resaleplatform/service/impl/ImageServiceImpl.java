package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.exception.PhotoUploadException;
import com.malina_ink.resaleplatform.repository.AdRepository;
import com.malina_ink.resaleplatform.repository.UserRepository;
import com.malina_ink.resaleplatform.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageService imageRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    @Value("${path.to.upload.user.image:src/main/resources/uploads/users/}")
    private String userpicPath;

    @Value("${path.to.upload.ads.image:src/main/resources/uploads/ads/}")
    private String adpicPath;


    public ImageServiceImpl(ImageService imageRepository, UserRepository userRepository, AdRepository adRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }

    private String saveImage(MultipartFile file, String fileName) throws PhotoUploadException {
        log.info("Вызван метод сохранения файла в директорию, формирования пути до файла");
        try {
            Files.write(Paths.get(fileName), file.getBytes());
            return fileName;
        } catch (IOException e) {
            throw new PhotoUploadException("Произошла ошибка при получении фото");
        }
    }

    @Override
    public String uploadUserImage(MultipartFile file, Integer userId) throws PhotoUploadException {
        log.info("Вызван метод загрузки аватара пользователя");
        String extension = StringUtils.getFilenameExtension(file.getName());

        String fileName = userpicPath + "/" + userId + "/" + UUID.randomUUID().toString() + "." + extension;
        return saveImage(file, fileName);
    }

    @Override
    public String uploadAdImage(MultipartFile file, Integer adId) throws PhotoUploadException {
        log.info("Вызван метод загрузки картинки объявления");
        String extension = StringUtils.getFilenameExtension(file.getName());
        String fileName = adpicPath + "/" + adId + "/" + UUID.randomUUID().toString() + "." + extension;
        return saveImage(file, fileName);
    }

    @Override
    public void deleteImage(String path) {
        log.info("Вызван метод удаления текущего фото по данному пути");
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}

