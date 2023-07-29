package com.malina_ink.resaleplatform.repository;

import org.springframework.stereotype.Repository;
import com.malina_ink.resaleplatform.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findByIdAndAdId(Integer commentId, Integer adId);
    List<Comment> findAllByAdId(Integer id);
    void deleteAllByAdId(Integer id);
    void deleteById(Integer id);

}
