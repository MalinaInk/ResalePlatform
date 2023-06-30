package com.malina_ink.resaleplatform.service;

import com.malina_ink.resaleplatform.dto.CommentDto;
import com.malina_ink.resaleplatform.entity.Comment;
import org.springframework.security.core.Authentication;

public interface CommentService {

    Comment getCommentById(long id);

    CommentDto createNewComment(Long Id, CommentDto commentDto, Authentication authentication);

    void deleteComments(long pk, long id,  Authentication authentication);

    CommentDto updateComments(long pk, long id, CommentDto commentDto, Authentication authentication);
}
