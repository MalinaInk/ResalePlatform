package com.malina_ink.resaleplatform.repository;

import com.malina_ink.resaleplatform.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findAllByAuthorIdOrderByIdDesc(Integer id);

    List<Ad> findAllByOrderByIdDesc();

    List<Ad> findAllByTitleContainingIgnoreCase(String search);
}
