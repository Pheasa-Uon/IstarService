package com.istar.service.Repository.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(Long userId);
    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);
    boolean existsByUserIdAndRoleId(Long userId, Long roleId);

    @Query("SELECT ur FROM UserRole ur WHERE ur.user.id = :userId AND ur.bStatus = true")
    List<UserRole> findByUserIdAndBStatusTrue(@Param("userId") Long userId);
}
