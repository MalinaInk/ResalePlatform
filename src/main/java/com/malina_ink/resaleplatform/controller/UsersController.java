package com.malina_ink.resaleplatform.controller;

import org.hibernate.annotations.Parent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UsersController {

////        private final ParentService parentService;
////        private final ParentRepository parentRepository;
////
////        public ParentController(ParentService parentService, ParentRepository parentRepository) {
////            this.parentService = parentService;
////            this.parentRepository = parentRepository;
////        }
//
//    //        @Operation(summary = "setPassword",
////                responses = {
////                        @ApiResponse(
////                                responseCode = "200",
////                                description = "OK",
////                                content = @Content(
////                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
////                                        schema = @Schema(implementation = Users.class,
////                                                $ref: '#/components/schemas/NewPassword')
////                                )
////                        ),
////                        @ApiResponse(
////                                responseCode = "500",
////                                description = "Произошла ошибка",
////                                content = @Content(
////                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
////                                        schema = @Schema(implementation = Users.class,
////
////                                )
////                        )
////                })
//    @PostMapping("/set_password")
//    public RequestBody create(@RequestBody NewPasswordDto newPasswordDto) {
//        return userService.getUser().setNewPassword(id);
//    }
//
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
//    @GetMapping("/me")
//    public User read(@PathVariable long id) {
//        return UserService.getUser(id);
//    }
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
//            return ResponseEntity.ok(user1);
//
//    }
//
//    //        @Operation(
////                summary = "Удалить данные усыновителя из базы",
////                responses = {
////                        @ApiResponse(
////                                responseCode = "200",
////                                description = "Данные успешно удалены",
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
//    @PutMapping("/me/image")
//    public String update(@RequestBody MultipartFile image) {
////    вызов метода
//        User user1 = userService.updateImage(image);
//
//        return String pathToPhoto;
//    }
}
