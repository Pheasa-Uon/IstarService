package com.istar.service.repository;

import com.istar.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // ✅ safe Optional
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByLoginToken(String loginToken);

}
