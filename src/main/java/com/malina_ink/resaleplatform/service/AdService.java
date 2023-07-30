package com.malina_ink.resaleplatform.service;


import com.malina_ink.resaleplatform.dto.AdDto;
import com.malina_ink.resaleplatform.dto.AdsDto;
import com.malina_ink.resaleplatform.dto.CreateOrUpdateAdDto;
import com.malina_ink.resaleplatform.dto.ExtendedAdDto;
import com.malina_ink.resaleplatform.service.impl.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для работы с объявлениями.
 */
public interface AdService {
    /**
     * @param image          файл изображения для объявления
     * @param createAds      объект CreateOrUpdateAdDto, содержащий свойства объявления
     * @param principal объект аутентификации пользователя, создающего объявление
     * @return AdsDto объект, представляющий созданное объявление
     */
    AdDto createAds(CreateOrUpdateAdDto createAds, MultipartFile image, UserPrincipal principal);

    /**
     * Обновляет объявление с указанным идентификатором с помощью переданных свойств.
     *
     * @param adsId     идентификатор объявления для обновления
     * @param createAds объект CreateOrUpdateAdDto, содержащий новые свойства объявления
     * @return AdsDto объект, представляющий обновленное объявление
     */
    AdDto updateAds(CreateOrUpdateAdDto createAds, Integer adsId, UserPrincipal userPrincipal);

    /**
     * Обновляет изображение объявления с указанным идентификатором.
     *
     * @param adsId    идентификатор объявления, для которого нужно обновить изображение
     * @param image новый файл изображения
     */
    String updateImage(Integer adsId, MultipartFile image, UserPrincipal principal);

    /**
     * Возвращает список всех объявлений в виде объекта AdsDto.
     *
     * @return объект AdsDto, содержащий список всех объявлений
     */
    AdsDto getAllAds(String title);

    /**
     * Возвращает список всех объявлений, созданных текущим пользователем, в виде объекта AdsDto.
     *
     * @param principal объект аутентификации текущего пользователя
     * @return объект AdsDto, содержащий список всех объявлений, созданных текущим пользователем
     */
    AdsDto getAdsMe(UserPrincipal principal);

    /**
     * Возвращает объявление с указанным идентификатором в виде объекта FullAdsDto.
     *
     * @param adsId идентификатор объявления для поиска
     * @return объект FullAdsDto, представляющий найденное объявление
     */
    ExtendedAdDto getById(Integer adsId);

    /**
     * Удаляет объявление с указанным идентификатором.
     *
     * @param adsId идентификатор объявления для удаления
     */
    void deleteAds(Integer adsId, UserPrincipal principal);
}
