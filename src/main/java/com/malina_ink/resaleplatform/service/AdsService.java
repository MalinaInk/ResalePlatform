package com.malina_ink.resaleplatform.service;


import com.malina_ink.resaleplatform.dto.AdDto;
import com.malina_ink.resaleplatform.entity.Ad;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface AdsService {

    Ad getAllAds();

    AdDto getAdsById(long id);

    AdDto createAd(AdDto adDto, MultipartFile image, Authentication authentication);

    void removeAds(long id, Authentication authentication);

    AdDto updateAdById(long id, AdDto adDto, Authentication authentication);

    AdDto getAllAdsForUser(String username);
}
