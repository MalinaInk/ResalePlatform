package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.*;
import com.malina_ink.resaleplatform.entity.Ad;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.exception.NotCorrectCostException;
import com.malina_ink.resaleplatform.exception.ObjectAbsenceException;
import com.malina_ink.resaleplatform.mapper.AdMapper;
import com.malina_ink.resaleplatform.mapper.ExtendedAdMapper;
import com.malina_ink.resaleplatform.repository.AdRepository;
import com.malina_ink.resaleplatform.repository.UserRepository;
import com.malina_ink.resaleplatform.service.AdService;
import com.malina_ink.resaleplatform.service.ImageService;
import com.malina_ink.resaleplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Сервис AdsServiceImpl
 * Сервис для добавления, удаления, редактирования и поиска объявлений в базе данных
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ExtendedAdMapper extendedAdMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ImageService imageService;


    /**
     * Получение списка всех объявлений из базы данных
     *
     * @return список(коллекцию) объявлений
     */
    @Override
    public AdsDto getAllAds(String title) {
        log.info("Вызван метод получения всех объявлений");
        if (title == null) {
            return adMapper.toAdsDto(adRepository.findAll());
        }
        return adMapper.toAdsDto(adRepository.findAllByTitleContainingIgnoreCase(title));
    }

    /**
     * Добавление нового объявления и сохранение его в базе данных
     *
     * @param createAds      данные объявления
     * @param image          картинка объявления
     * @param authentication авторизованный пользователь
     * @return добавленное новое объявление
     */
    @Override
    public AdDto createAds(CreateOrUpdateAdDto createAds, MultipartFile image, Authentication authentication) {
        if (createAds.getPrice() < 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0!");
        }

        log.info("Вызван метод добавления объявления");
        Ad adsEntity = new Ad();
        adsEntity.setTitle(createAds.getTitle());
        adsEntity.setPrice(createAds.getPrice());
        adsEntity.setDescription(createAds.getDescription());
        User author = userRepository.getUserByEmailIgnoreCase(authentication.getName()).orElseThrow(RuntimeException::new); //сделать свое исключение
        adsEntity.setUser(author);
        adRepository.save(adsEntity);

        try {
            String filePath = imageService.uploadAdImage(image, adsEntity.getPk());
            adsEntity.setAdImage(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке фото");
        }

        Ad created = adRepository.save(adsEntity);
        return adMapper.toDto(created);
    }

    /**
     * Получение объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param adsId идентификатор объявления, не может быть null
     * @return возвращает объявление по идентификатору (id)
     */
    @Override
    public ExtendedAdDto getById(Integer adsId) {
        log.info("Вызван метод получения объявления по идентификатору (id)");
        return extendedAdMapper.toDto(adRepository.findById(adsId).orElseThrow());
    }

    /**
     * Удаление объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param adsId идентификатор объявления, не может быть null
     */
    @Override
    public void deleteAds(Integer adsId) {
        log.info("Вызван метод удаления объявления по идентификатору (id)");
        adRepository.deleteById(adsId);
    }

    /**
     * Обновление объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param adsId     идентификатор объявления, не может быть null
     * @param createAds данные объявления
     * @return возвращает обновленное объявление по идентификатору (id)
     */
    @Override
    public AdDto updateAds(CreateOrUpdateAdDto createAds, Integer adsId) {
        if (adsId == null) {
            throw new ObjectAbsenceException("Такого объявления не существует!");
        }

        if (createAds.getPrice() < 0) {
            throw new NotCorrectCostException("Цена должна быть больше 0!");
        }

        Ad updateAd = adRepository.findById(adsId).orElseThrow(RuntimeException::new);
        updateAd.setTitle(createAds.getTitle());
        updateAd.setPrice(createAds.getPrice());
        updateAd.setDescription(createAds.getDescription());

        adRepository.save(updateAd);

        return adMapper.toDto(updateAd);
    }

    /**
     * Получение объявлений авторизованного пользователя, хранящихся в базе данных
     *
     * @param authentication авторизованный пользователь
     * @return возвращает все объявления авторизованного пользователя
     */
    @Override
    public AdsDto getAdsMe(Integer id, Authentication authentication) {
        log.info("Вызван метод получения объявлений авторизованного пользователя");
        return adMapper.toAdsDto(
                adRepository.findAllByAuthorIdOrderByIdDesc(userService.getUser(id, authentication).getId())
        );

    }

    /**
     * Обновление картинки объявления
     *
     * @param adsId идентификатор объявления, не может быть null
     * @param image картинка объявления
     * @return объявление с новой картинкой
     */
    @Override
    public String updateImage(Integer adsId, MultipartFile image) {
        log.info("Вызван метод обновления картинки объявления");
        if (adsId == null) {
            throw new ObjectAbsenceException("Такого объявления не существует!");
        }
        Ad updateAd = adRepository.findById(adsId).orElseThrow(RuntimeException::new);
        imageService.deleteImage(updateAd.getAdImage());

        try {
            String filePath = imageService.uploadAdImage(image, adsId);
            updateAd.setAdImage(filePath);
            adRepository.save(updateAd);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке фото");
        }
        return updateAd.getAdImage();
    }
}

