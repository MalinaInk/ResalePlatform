package com.malina_ink.resaleplatform.controller;

import com.malina_ink.resaleplatform.dto.NewPasswordDto;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/ads")
public class AdsController {

//    @GetMapping("/")
//    public responseWrapperAds read(@PathVariable long id) {
//        return AdsService.getAllAds(userId);
//    }
//
//
//    @PostMapping("/")
//    public RequestBody create(@RequestBody NewPasswordDto newPasswordDto) {
//        return AdsService.addAds();
//    }
//
//    @GetMapping("/{ad_pk}/comment")
//    public String read(@PathVariable int id) {
//        return AdsService.getComments(adsId);
//    }
//
//    @PostMapping("/{ad_pk}/comment")
//    public String create(@PathVariable String ads) {
//        return AdsService.addComments(adsId);
//    }
//
//    @GetMapping("/{id}")
//    public String read(@PathVariable int id) {
//        return AdsService.getAds(adsId);
//    }
//    //        @Operation(summary = "setPassword",
////                responses = {
////                        @ApiResponse(
////                                responseCode = "200",
////                                description = "Усыновитель найден",
////                                content = @Content(
////                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
////                                        array = @ArraySchema(schema = @Schema(implementation = Parent.class))
////                                )
////                        ),
////                        @ApiResponse(
////                                responseCode = "500",
////                                description = "Произошла ошибка",
////                                content = @Content(
////                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
////                                        schema = @Schema(implementation = Parent.class)
////                                )
////                        )
////                })
////    @GetMapping("/")
////    public responseWrapperAds read(@PathVariable long id) {
////        return AdsService.getAllAds(userId);
////    }
//
//    //        @Operation(
////                summary = "Изменить данные об усыновителе",
////                responses = {
////                        @ApiResponse(
////                                responseCode = "200",
////                                description = "Данные об усыновителе успешно изменены",
////                                content = @Content(
////                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
////                                        schema = @Schema(implementation = Parent.class)
////                                )
////                        ),
////                        @ApiResponse(
////                                responseCode = "500",
////                                description = "Произошла ошибка",
////                                content = @Content(
////                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
////                                        schema = @Schema(implementation = Parent.class)
////                                )
////                        )
////                })
//    @PutMapping("/me")
//    public ResponseEntity<User> update(@RequestBody User user) {
//        User user1 = userService.update(user);
////            if (parent1 == null) {
////                return ResponseEntity.notFound().build();
////            }
//        return ResponseEntity.ok(user1);
//
//    }
//
//
}


