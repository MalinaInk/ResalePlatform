package com.malina_ink.resaleplatform.mapper;

import com.malina_ink.resaleplatform.dto.AdDto;
import com.malina_ink.resaleplatform.dto.AdsDto;
import com.malina_ink.resaleplatform.entity.Ad;
import com.malina_ink.resaleplatform.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AdMapper {
    public AdDto toDto(Ad ad) {
        AdDto dto = new AdDto();
        dto.setAuthor(ad.getUser().getId());
        dto.setImage(ad.getAdImage());
        dto.setPk(ad.getPk());
        dto.setPrice(ad.getPrice());
        dto.setTitle(ad.getTitle());
        return dto;
    }

    public Ad toEntity(AdDto dto) {
        Ad ad = new Ad();
        User user = new User();
        user.setId(dto.getAuthor());

        ad.setUser(user);
        ad.setAdImage(dto.getImage());
        ad.setPk(dto.getPk());
        ad.setPrice(dto.getPrice());
        ad.setTitle(dto.getTitle());
        return ad;
    }

    public List<Ad> toEntity(List<AdDto> allAds) {
        return allAds.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<AdDto> toDto(List<Ad> allAds) {
        return allAds.stream().map(this::toDto).collect(Collectors.toList());
    }

    public AdsDto toAdsDto(List<Ad> allAds) {
        AdsDto result = new AdsDto();
        result.setResults(toDto(allAds));
        result.setCount(allAds.size());
        return result;
    }
}
