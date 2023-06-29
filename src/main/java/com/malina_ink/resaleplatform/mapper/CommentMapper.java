package com.malina_ink.resaleplatform.mapper;

import com.malina_ink.resaleplatform.dto.CommentDto;
import com.malina_ink.resaleplatform.entity.Comment;
import com.malina_ink.resaleplatform.entity.User;

public class CommentMapper {
    public CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setAuthor(comment.getUser().getId());
        dto.setAuthorImage(comment.getUser().getImage());
        dto.setAuthorFirstName(comment.getUser().getFirstname());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setPk(comment.getId());
        dto.setText(comment.getText());
        return dto;
    }

    public Comment toEntity(CommentDto dto) {
        Comment comment = new Comment();
        User user = new User();
        user.setId(dto.getAuthor());
        comment.setUser(user);

        comment.setId(dto.getPk());
        comment.setCreatedAt(dto.getCreatedAt());
        comment.setText(dto.getText());
        return comment;
    }
}
