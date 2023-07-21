package com.malina_ink.resaleplatform.mapper;

import com.malina_ink.resaleplatform.dto.ExtendedAdDto;
import com.malina_ink.resaleplatform.entity.Ad;
import org.springframework.stereotype.Component;

@Component
public class ExtendedAdMapper {
    public ExtendedAdDto toDto(Ad ad) {
        ExtendedAdDto dto = new ExtendedAdDto();
        dto.setPk(ad.getPk());
        dto.setAuthorFirstName(ad.getUser().getFirstname());
        dto.setAuthorLastName(ad.getUser().getLastName());
        dto.setDescription(ad.getDescription());
        dto.setPrice(ad.getPrice());
        dto.setTitle(ad.getTitle());
        dto.setImage(ad.getAdImage());
        dto.setEmail(ad.getUser().getEmail());
        dto.setPhone(ad.getUser().getPhone());
        return dto;
    }
}
