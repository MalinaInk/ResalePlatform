package com.malina_ink.resaleplatform.repository;

import com.malina_ink.resaleplatform.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findAllById(Long id);
}
