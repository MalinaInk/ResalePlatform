package com.malina_ink.resaleplatform.controller;

import com.malina_ink.resaleplatform.dto.*;
import com.malina_ink.resaleplatform.repository.AdRepository;
import com.malina_ink.resaleplatform.repository.CommentRepository;
import com.malina_ink.resaleplatform.service.AdService;
import com.malina_ink.resaleplatform.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdService adsService;
    private final CommentService commentService;

    public AdsController(AdService adsService, CommentService commentService, AdRepository adRepository, CommentRepository commentRepository) {
        this.adsService = adsService;
        this.commentService = commentService;
    }


    /**
     * Получить комментарии объявления
     *
     * @param id идентификатор объявления, не может быть null
     * @return комментарии
     */
    @Operation(summary = "Получить комментарии объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NotFound"
                    )
            })
    @GetMapping("/{id}/comment")
    public ResponseEntity<CommentsDto> readComments(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    /**
     * Добавить комментарий к объявлению
     *
     * @param id          Id объявление, не может быть null
     * @param createOrUpdateCommentDto  данные комментария
     * @param authentication авторизованный пользователь
     * @return возвращает объект, содержащий данные созданного комментария
     */
    @Operation(summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CreateOrUpdateCommentDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "NotFound"
                    )
            })
    @PostMapping("/{id}/comment")
    public ResponseEntity<CommentDto> createComment(@PathVariable int id,
                                                                  @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                                                  @NonNull Authentication authentication) {
        return ResponseEntity.ok(commentService.addComment(id, createOrUpdateCommentDto, authentication));
    }


    /**
     * Обновить комментарий
     *
     * @param commentId идентификатор комментария, не может быть null
     * @param commentId   обновленный комментарий
     * @return обновленный комментарий
     */
    @Operation(summary = "Обновить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CreateOrUpdateCommentDto.class)
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
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable int adId,
                                                                  @PathVariable int commentId,
                                                                  @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, createOrUpdateCommentDto));
    }


    /**
     * Удалить комментарий
     *
     * @param commentId идентификатор комментария, не может быть null
     * @param adId    идентификатор объявления, не может быть null
     */
    @Operation(summary = "Удалить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok"
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
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Функция получения всех объявлений, хранящихся в базе данных
     *
     * @param title заголовок объявления
     * @return возвращает все объявления
     */
    @Operation(summary = "Получить все объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdsDto.class)
                                    )
                            )
                    )
            })
    @GetMapping
    public ResponseEntity<AdsDto> readAllAds(@RequestParam(required = false) String title) {
        return ResponseEntity.ok(adsService.getAllAds(title));
    }


    /**
     * Функция получения объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param id идентификатор объявления, не может быть null
     * @return возвращает объявление по идентификатору (id)
     */
    @Operation(summary = "Получить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAdDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "NotFound"
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> readExtendedAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adsService.getById(id));
    }


    /**
     * Функция добавление объявления
     *
     * @param createOrUpdateAdDto      данные объявления
     * @param image          картинка объявления
     * @param authentication авторизованный пользователь
     * @return возвращает объект, содержащий данные созданного объявления
     */
    @Operation(summary = "Добавить объявление",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CreateOrUpdateAdDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })

    @PostMapping
    public ResponseEntity<AdDto> createAd(@RequestPart("properties") @NotNull CreateOrUpdateAdDto createOrUpdateAdDto,
                                                        @RequestPart MultipartFile image,
                                                        @NonNull Authentication authentication) {
        return ResponseEntity.ok(adsService.createAds(createOrUpdateAdDto, image, authentication));
    }


    /**
     * Функция обновления объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param id        идентификатор объявления, не может быть null
     * @param createOrUpdateAdDto данные объявления
     * @return возвращает обновленное объявление по идентификатору (id)
     */
    @Operation(summary = "Обновить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDto.class)
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
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPasswordDto.class)
                            )
                    )
            })

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable int id,
                                                         @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return ResponseEntity.ok(adsService.updateAds(createOrUpdateAdDto, id));
    }


    /**
     * Функция удаления объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param id идентификатор объявления, не может быть null
     */
    @Operation(summary = "Удалить объявление",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content"
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
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPasswordDto.class)
                            )
                    )
            })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Integer id) {
        adsService.deleteAds(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Функция получения объявления авторизованного пользователя, хранящихся в базе данных
     *
     * @param authentication авторизованный пользователь
     * @return возвращает объявление авторизованного пользователя
     */
    @Operation(summary = "Получить объявления авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe(Integer id, Authentication authentication) {
       return ResponseEntity.ok(adsService.getAdsMe(id, authentication));
    }
}


