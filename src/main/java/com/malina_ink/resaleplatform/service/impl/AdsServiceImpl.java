package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.AdDto;
import com.malina_ink.resaleplatform.entity.Ad;
import com.malina_ink.resaleplatform.repository.AdRepository;
import com.malina_ink.resaleplatform.service.AdsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class AdsServiceImpl implements AdsService {
    private HashMap<Long, Ad> ads = new HashMap<>();
    private AdRepository adRepository;
    private CommentServiceImpl commentServiceImpl;
    private UserServiceImpl userService;

    public AdsServiceImpl(AdRepository adRepository, CommentServiceImpl commentServiceImpl, UserServiceImpl userService) {
        this.adRepository = adRepository;
        this.commentServiceImpl = commentServiceImpl;
        this.userService = userService;
    }

    @Override
    public Collection getAllAds() {
        return (Collection) ads;
    }

    @Override
    public AdDto getAdsById(long id) {
        return null;
    }

    @Override
    public AdDto createAd(AdDto adDto, MultipartFile image, Authentication authentication) {
        return null;
    }

    @Override
    public void removeAds(long id, Authentication authentication) {

    }

    @Override
    public AdDto updateAdById(long id, AdDto adDto, Authentication authentication) {
        return null;
    }

    @Override
    public AdDto getAllAdsForUser(String username) {
        return null;
    }
}
