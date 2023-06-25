package com.malina_ink.resaleplatform.dto;

import lombok.Data;
@Data
public class CreateOrUpdateAd {
    private String description;
    private int price;
    private String title;
}
