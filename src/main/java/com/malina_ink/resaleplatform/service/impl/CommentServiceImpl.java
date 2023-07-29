package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.CommentDto;
import com.malina_ink.resaleplatform.dto.CommentsDto;
import com.malina_ink.resaleplatform.dto.CreateOrUpdateCommentDto;
import com.malina_ink.resaleplatform.entity.Ad;
import com.malina_ink.resaleplatform.entity.Comment;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.exception.AccessErrorException;
import com.malina_ink.resaleplatform.mapper.CommentMapper;
import com.malina_ink.resaleplatform.repository.AdRepository;
import com.malina_ink.resaleplatform.repository.CommentRepository;
import com.malina_ink.resaleplatform.repository.UserRepository;
import com.malina_ink.resaleplatform.service.CommentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Сервис для методов работы с комментариями
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

    private final AdRepository adsRepository;

    private final UserRepository userRepository;

    private final CommentMapper commentMapper;


    /**
     * Позволяет получить все комментарии к определенному объявлению
     * <br> Использован метод репозитория {@link CommentRepository#findAllByAdId(Integer)}
     *
     * @param adsId идентификатор объявления, не может быть null
     * @return возвращает все комментарии к определенному объявлению
     */
    @Override
    public CommentsDto getComments(Integer adsId) {
        log.info("Вызван метод получения всех комментариев к определенному объявлению");
        return commentMapper.toCommentsDto(commentRepository.findAllByAdId(adsId));
    }

    /**
     * Позволяет добавить комментарий к определенному объявлению
     * <br> Использован метод репозитория {@link CommentRepository#save(Object)}
     *
     * @param adsId          идентификатор объявления, не может быть null
     * @param createComment  создание текста комментария
     * @param authentication авторизованный пользователь
     * @return возвращает добавленный комментарий
     */
    @Override
    public CommentDto addComment(@NotNull Integer adsId,
                                 CreateOrUpdateCommentDto createComment,
                                 Authentication authentication) {
        log.info("Вызван метод добавления комментария");
        Comment commentEntity = new Comment();

        Ad adsEntity = adsRepository.findById(adsId)
                .orElseThrow(RuntimeException::new);
        User author = userRepository.getUserByEmailIgnoreCase(authentication.getName())
                .orElseThrow(RuntimeException::new);
        commentEntity.setAd(adsEntity);
        commentEntity.setUser(author);
        commentEntity.setCreatedAt(LocalDateTime.now());
        commentEntity.setText(createComment.getText());
        Comment created = commentRepository.save(commentEntity);

        return commentMapper.toDto(created);
    }

    /**
     * Позволяет удалить комментарий
     * <br> Использован метод репозитория {@link CommentRepository#deleteById(Integer)}
     *
     * @param commentId идентификатор комментария, не может быть null
     *                  //     * @param adsId     идентификатор объявления, не может быть null
     */
    @Override
    public void deleteComment(Integer commentId, Authentication authentication) {
        log.info("Вызван метод удаления комментария по идентификатору (id)");
        //проверка на авторство редактируемого/удаляемого
        Comment updateComment = commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
        if (!Objects.equals(authentication.getName(), updateComment.getUser())) {
            throw new AccessErrorException("У вас нет прав на данную операцию");
            commentRepository.deleteById(commentId);
        }
    }

        /**
         * Позволяет изменить комментарий
         * <br> Использован метод репозитория {@link CommentRepository#findById}
         * <br> Использован метод репозитория {@link CommentRepository#save(Object)}
         *
         * @param commentId      идентификатор комментария, не может быть null
         * @param comment        измененный комментарий
         * @return возвращает измененный комментарий
         */
        @Override
        public CommentDto updateComment (Integer adsId,
                @NotNull Integer commentId,
                CreateOrUpdateCommentDto comment,
                Authentication authentication){
            log.info("Вызван метод обновления комментария по идентификатору (id)");
            Comment updateCommentEntity = commentRepository.findById(commentId)
                    .orElseThrow(RuntimeException::new);
            //проверка на авторство редактируемого/удаляемого
            if (!Objects.equals(authentication.getName(), updateCommentEntity.getUser())) {
                throw new AccessErrorException("У вас нет прав на данную операцию");
            updateCommentEntity.setText(comment.getText());
            commentRepository.save(updateCommentEntity);
            return commentMapper.toDto(updateCommentEntity);
        }
    }

