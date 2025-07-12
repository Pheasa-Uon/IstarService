package com.istar.service.Repository.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleFeaturePermissionRepository extends JpaRepository<RoleFeaturePermission, Long> {
    List<RoleFeaturePermission> findByRoleId(Long roleId);
    void deleteByRoleId(Long roleId);
}
