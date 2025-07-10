package com.istar.service.repository;

import com.istar.service.model.RoleFeaturePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleFeaturePermissionRepository extends JpaRepository<RoleFeaturePermission, Long> {
    List<RoleFeaturePermission> findByRoleId(Long roleId);
}
