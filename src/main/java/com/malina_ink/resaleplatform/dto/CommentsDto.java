package com.malina_ink.resaleplatform.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDto {
    private int count;
    private List<CommentDto> results;
}
