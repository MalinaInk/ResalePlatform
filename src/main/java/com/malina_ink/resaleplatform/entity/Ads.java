package com.malina_ink.resaleplatform.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class Ads {
    private int author;
    private String image;
    private String id;
    private int price;
    private String title;
    private String description;
    private String AdImage;

    int count = 0;
    private static List<Ads> ads = new ArrayList<>();

}
