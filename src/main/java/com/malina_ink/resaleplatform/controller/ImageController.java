package com.malina_ink.resaleplatform.controller;

import com.malina_ink.resaleplatform.service.impl.AdServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(value = "http://localhost:8080")
@RestController
@RequestMapping("/ads")
public class ImageController {
    private AdServiceImpl adService;

    public ImageController(AdServiceImpl adService) {
        this.adService = adService;
    }


    @Operation(summary = "Обновить картинку объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MultipartFile.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
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

    @PatchMapping("/{id}/image")
    public ResponseEntity<String> updateImage(@PathVariable Integer id, @RequestBody MultipartFile image) {
        adService.updateImage(id, image);
        return ResponseEntity.ok().build();
    }
}