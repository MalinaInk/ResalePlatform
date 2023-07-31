package com.malina_ink.resaleplatform.mapper;

import com.malina_ink.resaleplatform.dto.CommentDto;
import com.malina_ink.resaleplatform.dto.CommentsDto;
import com.malina_ink.resaleplatform.dto.CreateOrUpdateCommentDto;
import com.malina_ink.resaleplatform.entity.Comment;
import com.malina_ink.resaleplatform.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CommentMapper {
    public CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setAuthor(comment.getUser().getId());
//        dto.setAuthorImage(comment.getUser().getImage());
        dto.setAuthorImage(String.format("/images/user/%d/image", comment.getUser().getId()));
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

    public List<Comment> toEntity(List<CommentDto> allComments) {
        return allComments.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<CommentDto> toDto(List<Comment> allComments) {
        return allComments.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CommentsDto toCommentsDto(List<Comment> allComment) {
        CommentsDto result = new CommentsDto();
        result.setResults(toDto(allComment));
        result.setCount(allComment.size());
        return result;
    }
}
