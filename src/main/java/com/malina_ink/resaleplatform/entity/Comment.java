package com.malina_ink.resaleplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Comment {
    private int author;
    private String createdAd;
    private int id;
    private String text;
}
