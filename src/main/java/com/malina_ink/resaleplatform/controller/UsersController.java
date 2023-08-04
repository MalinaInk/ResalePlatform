package com.malina_ink.resaleplatform.controller;

import com.malina_ink.resaleplatform.dto.NewPasswordDto;
import com.malina_ink.resaleplatform.dto.UpdateUserDto;
import com.malina_ink.resaleplatform.dto.UserDto;
import com.malina_ink.resaleplatform.service.UserService;
import com.malina_ink.resaleplatform.service.impl.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UsersController {
    private final UserService userServiceImpl;

    public UsersController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * Обновление пароля
     *
     * @param newPasswordDto   новый пароль
     * @param authentication авторизованный пользователь
     * @return новый пароль для авторизованного пользователя
     */
    @Operation(summary = "Обновление пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            })
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> updatePassword(@RequestBody NewPasswordDto newPasswordDto, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        userServiceImpl.setNewPassword(newPasswordDto, principal);
        return ResponseEntity.ok().build();
    }

    /**
     * Получение информации об авторизованном пользователе
     *
     * @param authentication авторизованный пользователь
     * @return информацию об авторизованном пользователе
     */
    @Operation(summary = "Получить информацию об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )

            })
    @GetMapping("/me")
    public ResponseEntity<UserDto> readUser(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        return ResponseEntity.ok(userServiceImpl.getUser(principal));
    }

    /**
     * Обновить информацию об авторизованном пользователе
     *
     * @param updateUser      изменения, вносимые пользователем
     * @param authentication авторизованный пользователь
     * @return обновленную информацию об авторизованном пользователе
     */
    @Operation(
            summary = "Обновить информацию об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateUserDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )

            })

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUser, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        return ResponseEntity.ok(userServiceImpl.updateUser(updateUser, principal));
    }

    /**
     * Обновить аватар авторизованного пользователя
     *
     * @param image аватар авторизованного пользователя
     * @return обновленный аватар авторизованного пользователя
     */
    @Operation(
            summary = "Обновить аватар авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok"

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unauthorized"

                    )
            })



    @PatchMapping("/me/image")
    public ResponseEntity<String> updateUserImage(@RequestBody MultipartFile image, Authentication authentication) throws IOException {
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        userServiceImpl.updateUserImage(image, principal);
        return ResponseEntity.ok().build();
    }
}
