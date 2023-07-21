package com.malina_ink.resaleplatform.repository;

import com.malina_ink.resaleplatform.entity.Ad;
import com.malina_ink.resaleplatform.entity.Comment;
import com.malina_ink.resaleplatform.entity.User;
import com.malina_ink.resaleplatform.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByEmailIgnoreCase(String email);

    Optional<User> findByRole(Role role);

    boolean existsByEmailIgnoreCase(String email);

}
