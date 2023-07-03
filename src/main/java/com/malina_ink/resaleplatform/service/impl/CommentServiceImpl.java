package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.CommentDto;
import com.malina_ink.resaleplatform.entity.Comment;
import com.malina_ink.resaleplatform.repository.CommentRepository;
import com.malina_ink.resaleplatform.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class CommentServiceImpl implements CommentService {
    private HashMap<Long, Comment> comments = new HashMap<>();
    private CommentRepository commentRepository;
    private AdsServiceImpl commentServiceImpl;
    private UserServiceImpl userService;

    public CommentServiceImpl(CommentRepository commentRepository, AdsServiceImpl commentServiceImpl, UserServiceImpl userService) {
        this.commentRepository = commentRepository;
        this.commentServiceImpl = commentServiceImpl;
        this.userService = userService;
    }

    @Override
    public Comment getCommentById(long id) {
        return null;
    }

    @Override
    public CommentDto createNewComment(Long Id, CommentDto commentDto, Authentication authentication) {
        return null;
    }

    @Override
    public void deleteComments(long pk, long id, Authentication authentication) {

    }

    @Override
    public CommentDto updateComments(long pk, long id, CommentDto commentDto, Authentication authentication) {
        return null;
    }
}
