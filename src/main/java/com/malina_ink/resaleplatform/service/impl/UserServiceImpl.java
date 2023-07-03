package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.UserDto;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.repository.UserRepository;
import com.malina_ink.resaleplatform.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    private HashMap<Long, User> users = new HashMap<>();

    private UserRepository commentRepository;
    private AdsServiceImpl commentServiceImpl;
    private CommentServiceImpl userServiceImpl;
    private AuthServiceImpl authServiceImpl;

    public UserServiceImpl(UserRepository commentRepository,
                           AdsServiceImpl commentServiceImpl,
                           CommentServiceImpl userServiceImpl,
                           AuthServiceImpl authServiceImpl) {
        this.commentRepository = commentRepository;
        this.commentServiceImpl = commentServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.authServiceImpl = authServiceImpl;
    }

    @Override
    public Collection getAllUsers() {
        return (Collection) users;
    }

    @Override
    public UserDto updateUser(UserDto userdto, String name) {
        return null;
    }

    @Override
    public void updateUserAvatar(String username, MultipartFile file) {

    }

    @Override
    public byte[] getUserAvatar(long id) {
        return new byte[0];
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public void checkIfUserHasPermissionToAlter(Authentication authentication, String username) {

    }
}
