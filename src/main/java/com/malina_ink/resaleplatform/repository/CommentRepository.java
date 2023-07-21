package com.malina_ink.resaleplatform.repository;

import org.springframework.stereotype.Repository;
import com.malina_ink.resaleplatform.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findByIdAndAdsId(Integer commentId, Integer adId);
    List<Comment> findAllByAdsId(Integer id);
    void deleteAllByAds_Id(Integer id);
    void deleteById(Integer id);

}
