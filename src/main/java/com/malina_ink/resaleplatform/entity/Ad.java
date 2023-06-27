package com.malina_ink.resaleplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Ad {

    private String id;

    private int author;
    private String image;
    private int price;
    private String title;
    private String description;
    private String AdImage;
    private List<Comment> comments = new ArrayList<>();


}
