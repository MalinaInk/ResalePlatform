package com.malina_ink.resaleplatform.repository;

import org.springframework.stereotype.Repository;
import com.malina_ink.resaleplatform.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllById(Long id);
}
