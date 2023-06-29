package com.malina_ink.resaleplatform.repository;

import com.malina_ink.resaleplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findUserByUsername (String username);
}
