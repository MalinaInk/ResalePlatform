package com.malina_ink.resaleplatform.controller;

import com.malina_ink.resaleplatform.service.UserService;
import com.malina_ink.resaleplatform.service.impl.AdServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/images")
public class ImageController {
    private AdServiceImpl adService;
    private UserService userService;

    public ImageController(AdServiceImpl adService, UserService userService) {
        this.adService = adService;
        this.userService = userService;
    }

    /**
     * Получить картинку объявления
     *
     * @param id идентификатор объявления
     * @return массив байтов нужного файла
     */

    @Operation(
            summary = "Получить картинку объявления",
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
    @GetMapping(value = "/ad/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAdImage(@PathVariable Integer id) {
        return adService.getImage(id);
    }

    /**
     * Получить аватар авторизованного пользователя
     *
     * @param id идентификатор пользователя
     * @return массив байтов нужного файла
     */

    @Operation(
            summary = "Получить аватар авторизованного пользователя",
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

    @GetMapping(value = "/user/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getUserImage(@PathVariable Integer id) {
        return userService.getImage(id);
    }
}