package com.istar.service.repository;


import com.istar.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // ✅ safe Optional
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByLoginToken(String loginToken);
    List<User> findBybStatusTrue();

    @Query("SELECT u FROM User u WHERE u.bStatus = true AND " +
            "(" +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            ")")
    List<User> searchActiveUsersByKeyword(@Param("keyword") String keyword);


    @Query("SELECT MAX(r.rolesCode) FROM Role r")
    String findMaxUserCode();


}
