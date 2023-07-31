package com.malina_ink.resaleplatform.mapper;

import com.malina_ink.resaleplatform.dto.ExtendedAdDto;
import com.malina_ink.resaleplatform.entity.Ad;
import org.springframework.stereotype.Component;

@Component
public class ExtendedAdMapper {
    public ExtendedAdDto toDto(Ad ad) {
        ExtendedAdDto dto = new ExtendedAdDto();
        dto.setPk(ad.getId());
        dto.setAuthorFirstName(ad.getAuthor().getFirstname());
        dto.setAuthorLastName(ad.getAuthor().getLastName());
        dto.setDescription(ad.getDescription());
        dto.setPrice(ad.getPrice());
        dto.setTitle(ad.getTitle());
//        dto.setImage(ad.getAdImage());
        dto.setImage(String.format("/images/ad/%d/image",ad.getId()));
        dto.setEmail(ad.getAuthor().getEmail());
        dto.setPhone(ad.getAuthor().getPhone());
        return dto;
    }
}
