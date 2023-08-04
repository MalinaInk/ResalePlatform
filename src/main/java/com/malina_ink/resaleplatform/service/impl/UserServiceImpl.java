package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.NewPasswordDto;
import com.malina_ink.resaleplatform.dto.UpdateUserDto;
import com.malina_ink.resaleplatform.dto.UserDto;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.enums.Role;
import com.malina_ink.resaleplatform.exception.ObjectAbsenceException;
import com.malina_ink.resaleplatform.mapper.UserMapper;
import com.malina_ink.resaleplatform.repository.UserRepository;
import com.malina_ink.resaleplatform.service.ImageService;
import com.malina_ink.resaleplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(User.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Обновление пароля пользователя
     *
     * @param newPassword    новый пароль
     * @param principal авторизованный пользователь
     * @return новый пароль для авторизованного пользователя
     */
    @Override
    public void setNewPassword(NewPasswordDto newPassword, UserPrincipal principal) {
        logger.info("Вызван метод обновления пароля пользователя");
        User user = userRepository.getUserByEmailIgnoreCase(principal.getUsername())
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует"));
        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
        userMapper.toDto(user);
    }

    /**
     * Получение информации об авторизованном пользователе
     *
     * @param principal авторизованный пользователь
     * @return информацию об авторизованном пользователе
     */
    @Override
    public UserDto getUser(UserPrincipal principal) {
        logger.info("Вызван метод получения информации об авторизованном пользователе");
        return userMapper.toDto(userRepository.getUserByEmailIgnoreCase(principal.getUsername())
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует")));
    }

    /**
     * Обновить информацию об авторизованном пользователе
     *
     * @param user           пользователь
     * @param principal авторизованный пользователь
     * @return обновленную информацию об авторизованном пользователе
     */
    @Override
    public UserDto updateUser(UpdateUserDto user, UserPrincipal principal) {
        logger.info("Вызван метод обновления информации об авторизованном пользователе");
        User userEntity = userRepository.getUserByEmailIgnoreCase(principal.getUsername())
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует"));
        userEntity.setFirstname(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    /**
     * Поменять роль авторизованного пользователя
     *
     * @param id     идентификатор авторизованного пользователя
     * @param role роль пользователя
     */
    @Override
    public UserDto updateRole(Integer id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectAbsenceException("Такого пользователя не существует"));
        user.setRole(role);
        return userMapper.toDto(user);
    }

    /**
     * Обновить аватар авторизованного пользователя
     *
     * @param image          аватар авторизованного пользователя
     * @param principal авторизованный пользователь
     */
    @Override
    public void updateUserImage(MultipartFile image, UserPrincipal principal) throws IOException {
        logger.info("Вызван метод обновления аватара авторизованного пользователя");
        User user = userRepository.getUserByEmailIgnoreCase(principal.getUsername())
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует"));
        String imageUser = imageService.uploadUserImage(image, user.getId());
        user.setImage(imageUser);;
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmailIgnoreCase(email)
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует"));
    }


    /**
     * Получить аватар пользователя
     *
     * @param id идентификатор пользователя
     * @return массив байтов нужного файла
     */

    @Override
    public byte[] getImage(Integer id) {
        String pathToFile = userRepository.findById(id)
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует"))
                .getImage();
        return imageService.getImageBytes(pathToFile);
    }

}


