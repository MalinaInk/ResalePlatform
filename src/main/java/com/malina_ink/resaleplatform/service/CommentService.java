package com.malina_ink.resaleplatform.service;

import com.malina_ink.resaleplatform.dto.CommentDto;
import com.malina_ink.resaleplatform.dto.CommentsDto;
import com.malina_ink.resaleplatform.dto.CreateOrUpdateCommentDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Сервис для работы с комментариями.
 */
public interface CommentService {
    /**
     * Метод для добавления комментария к объявлению.
     *
     * @param adsId               идентификатор объявления.
     * @param createComment данные для создания комментария.
     * @param authentication   данные аутентификации пользователя.
     * @return Добавленный комментарий в формате CommentDto.
//     * @throws IncorrectArgumentException если текст комментария пустой.
     */
    CommentDto addComment(@NotNull Integer adsId, CreateOrUpdateCommentDto createComment, Authentication authentication);


    /**
     * Метод для удаления комментария по идентификатору объявления и комментария.
     *
//     * @param Id      идентификатор объявления.
     * @param commentId идентификатор комментария.
     */
    void deleteComment(Integer commentId);

    /**
     * Метод для получения списка комментариев по идентификатору объявления.
     *
     * @param adsId идентификатор объявления.
     * @return список комментариев.
     */
    CommentsDto getComments(Integer adsId);

    /**
     * Метод для обновления комментария по идентификатору объявления и комментария.
     *
     * @param adsId       идентификатор объявления.
     * @param commentId  идентификатор комментария.
     * @param comment данные для обновления комментария.
     * @return Обновленный комментарий в формате CommentDto, если обновление прошло успешно, иначе - null.
//     * @throws IncorrectArgumentException если текст комментария пустой.
     */
    CommentDto updateComment(Integer adsId, @NotNull Integer commentId, CreateOrUpdateCommentDto comment);
}

