package com.malina_ink.resaleplatform.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDto {
    private int count;
    private List<AdDto> results;
}
