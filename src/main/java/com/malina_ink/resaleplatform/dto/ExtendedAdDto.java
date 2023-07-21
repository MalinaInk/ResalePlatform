package com.malina_ink.resaleplatform.dto;

import lombok.Data;
@Data
public class ExtendedAdDto {
    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private int price;
    private String title;
    private String image;
    private String email;
    private String phone;
}
