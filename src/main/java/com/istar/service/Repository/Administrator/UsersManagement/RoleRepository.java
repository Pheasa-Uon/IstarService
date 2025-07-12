package com.istar.service.Repository.Administrator.UsersManagement;


import com.istar.service.Entity.Administrator.UsersManagment.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRolesCode(String rolesCode);
    List<Role> findBybStatusTrue();

    @Query("SELECT r FROM Role r WHERE r.bStatus = true AND " +
            "(" +
            "LOWER(r.rolesCode) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            ")")
    List<Role> searchActiveRolesByKeyword(@Param("keyword") String keyword);

    @Query("SELECT MAX(u.rolesCode) FROM Role u")
    String findMaxUserCode();
}
