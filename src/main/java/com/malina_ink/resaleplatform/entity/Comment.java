package com.malina_ink.resaleplatform.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Comment {
    private int author;
    private String createdAd;
    private int id;
    private String text;

    int count = 0;
    private static List<Comment> comments = new ArrayList<>();


}
