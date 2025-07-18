package com.istar.service.Repository.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Entity.Administrator.UsersManagment.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleFeaturePermissionRepository extends JpaRepository<RoleFeaturePermission, Long> {
    List<RoleFeaturePermission> findByRoleId(Long roleId);
    Optional<RoleFeaturePermission> findByRoleIdAndFeatureId(Long roleId, Long featureId);
    List<RoleFeaturePermission> findByRole(Role role);
}
