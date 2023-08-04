package com.malina_ink.resaleplatform.service;

import com.malina_ink.resaleplatform.dto.CommentDto;
import com.malina_ink.resaleplatform.dto.CommentsDto;
import com.malina_ink.resaleplatform.dto.CreateOrUpdateCommentDto;
import com.malina_ink.resaleplatform.service.impl.UserPrincipal;
import jakarta.validation.constraints.NotNull;

/**
 * Сервис для работы с комментариями.
 */
public interface CommentService {
    /**
     * Метод для добавления комментария к объявлению.
     *
     * @param adsId               идентификатор объявления.
     * @param createComment данные для создания комментария.
     * @param userPrincipal   данные аутентификации пользователя.
     * @return Добавленный комментарий в формате CommentDto.
//     * @throws IncorrectArgumentException если текст комментария пустой.
     */
    CommentDto addComment(@NotNull Integer adsId, CreateOrUpdateCommentDto createComment, UserPrincipal userPrincipal);


    /**
     * Метод для удаления комментария по идентификатору объявления и комментария.
     *
//     * @param Id      идентификатор объявления.
     * @param commentId идентификатор комментария.
     */
    void deleteComment(Integer commentId, UserPrincipal principal);

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
     * @param commentId  идентификатор комментария.
     * @param comment данные для обновления комментария.
     * @return Обновленный комментарий в формате CommentDto, если обновление прошло успешно, иначе - null.
//     * @throws IncorrectArgumentException если текст комментария пустой.
     */
    CommentDto updateComment(@NotNull Integer commentId, CreateOrUpdateCommentDto comment, UserPrincipal principal);
}

