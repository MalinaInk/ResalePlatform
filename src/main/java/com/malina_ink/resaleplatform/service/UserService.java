package com.malina_ink.resaleplatform.service;

import com.malina_ink.resaleplatform.dto.UserDto;
import com.malina_ink.resaleplatform.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserDto getAllUsers();

    UserDto updateUser(UserDto userdto, String name);

    void updateUserAvatar(String username, MultipartFile file);

    byte[] getUserAvatar(long id);

    User getUserByUsername(String username);

    void checkIfUserHasPermissionToAlter(Authentication authentication, String username);
}
