package com.malina_ink.resaleplatform.service;

import com.malina_ink.resaleplatform.dto.NewPasswordDto;
import com.malina_ink.resaleplatform.dto.UpdateUserDto;
import com.malina_ink.resaleplatform.dto.UserDto;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    /**
     * Устанавливает новый пароль для текущего пользователя.
     *
     * @param newPassword    данные нового пароля
     * @param authentication данные аутентификации текущего пользователя
     * @throws IllegalArgumentException если текущий пароль не совпадает с сохраненным в базе данных
     */
    void setNewPassword(NewPasswordDto newPassword, Authentication authentication);

    /**
     * Получает данные текущего пользователя.
     *
     * @param authentication данные аутентификации текущего пользователя
     * @return данные текущего пользователя в виде объекта UserDto
     */
    UserDto getUser(Integer userId, Authentication authentication);

    /**
     * Обновляет данные текущего пользователя.
     *
     * @param userDto        новые данные пользователя
     * @param authentication данные аутентификации текущего пользователя
     * @return обновленные данные пользователя в виде объекта UserDto
     * @throws IllegalArgumentException если не заполнены все поля в объекте UserDto
     */
    UserDto updateUser(UpdateUserDto userDto, Authentication authentication);

    /**
     * Обновляет роль пользователя.
     *
     * @param id   идентификатор пользователя
     * @param role новая роль пользователя
     * @return обновленные данные пользователя в виде объекта UserDto
//     * @throws UserNotFoundException если пользователь не найден в базе данных
     */
    UserDto updateRole(Integer id, Role role);

    /**
     * Обновляет аватар пользователя.
     *
     * @param image          файл нового аватара пользователя
     * @param authentication данные аутентификации текущего пользователя
     */
    void updateUserImage(MultipartFile image, Authentication authentication) throws IOException;

    /**
     * Получает пользователя по его email.
     *
     * @param email email пользователя
     * @return данные пользователя в виде объекта User
//     * @throws UserNotFoundException если пользователь не найден в базе данных
     */
    User getUserByEmail(String email);
}
