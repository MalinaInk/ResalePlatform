package com.malina_ink.resaleplatform.service.impl;

import com.malina_ink.resaleplatform.dto.CommentDto;
import com.malina_ink.resaleplatform.dto.CommentsDto;
import com.malina_ink.resaleplatform.dto.CreateOrUpdateCommentDto;
import com.malina_ink.resaleplatform.entity.Ad;
import com.malina_ink.resaleplatform.entity.Comment;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.enums.Role;
import com.malina_ink.resaleplatform.exception.AccessErrorException;
import com.malina_ink.resaleplatform.exception.ObjectAbsenceException;
import com.malina_ink.resaleplatform.mapper.CommentMapper;
import com.malina_ink.resaleplatform.repository.AdRepository;
import com.malina_ink.resaleplatform.repository.CommentRepository;
import com.malina_ink.resaleplatform.repository.UserRepository;
import com.malina_ink.resaleplatform.service.CommentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
     * @param adsId         идентификатор объявления, не может быть null
     * @param createComment создание текста комментария
     * @param principal     авторизованный пользователь
     * @return возвращает добавленный комментарий
     */
    @Override
    public CommentDto addComment(@NotNull Integer adsId,
                                 CreateOrUpdateCommentDto createComment,
                                 UserPrincipal principal) {
        log.info("Вызван метод добавления комментария");
        Comment commentEntity = new Comment();

        Ad adsEntity = adsRepository.findById(adsId)
                .orElseThrow(() ->  new ObjectAbsenceException("Такого комментария не существует"));
        User author = userRepository.getUserByEmailIgnoreCase(principal.getUsername())
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует"));
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
    public void deleteComment(Integer commentId, UserPrincipal principal) {
        log.info("Вызван метод удаления комментария по идентификатору (id)");
        Comment updateComment = commentRepository.findById(commentId)
                .orElseThrow(() ->  new ObjectAbsenceException("Такого комментария не существует"));
        User user = userRepository.getUserByEmailIgnoreCase(principal.getUsername())
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует"));
        if (user.getId() != updateComment.getUser().getId() && !user.getRole().equals(Role.ADMIN)) {
            throw new AccessErrorException("У вас нет прав на данную операцию");
        }
        commentRepository.deleteById(commentId);
    }

    /**
     * Позволяет изменить комментарий
     * <br> Использован метод репозитория {@link CommentRepository#findById}
     * <br> Использован метод репозитория {@link CommentRepository#save(Object)}
     *
     * @param commentId идентификатор комментария, не может быть null
     * @param comment   измененный комментарий
     * @return возвращает измененный комментарий
     */
    @Override
    public CommentDto updateComment(@NotNull Integer commentId,
                                    CreateOrUpdateCommentDto comment,
                                    UserPrincipal principal) {
        log.info("Вызван метод обновления комментария по идентификатору (id)");

        Comment updateComment = commentRepository.findById(commentId)
                .orElseThrow(() ->  new ObjectAbsenceException("Такого комментария не существует"));
        User user = userRepository.getUserByEmailIgnoreCase(principal.getUsername())
                .orElseThrow(() ->  new ObjectAbsenceException("Такого пользователя не существует"));
        if (user.getId() != updateComment.getUser().getId() && !user.getRole().equals(Role.ADMIN)) {
            throw new AccessErrorException("У вас нет прав на данную операцию");
        }
        updateComment.setText(comment.getText());
        commentRepository.save(updateComment);
        return commentMapper.toDto(updateComment);
    }
}

