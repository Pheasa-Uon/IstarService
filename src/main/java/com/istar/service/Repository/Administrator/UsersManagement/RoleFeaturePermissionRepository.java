package com.istar.service.Repository.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Entity.Administrator.UsersManagment.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface RoleFeaturePermissionRepository extends JpaRepository<RoleFeaturePermission, Long> {
    List<RoleFeaturePermission> findByRoleId(Long roleId);
    Optional<RoleFeaturePermission> findByRoleIdAndFeatureId(Long roleId, Long featureId);
    List<RoleFeaturePermission> findByRole(Role role);
    List<RoleFeaturePermission> findByRoleIdIn(List<Long> roleIds);
    @Query("SELECT rfp FROM RoleFeaturePermission rfp WHERE rfp.role.id IN :roleIds AND rfp.bStatus = true")
    List<RoleFeaturePermission> findByRoleIdInAndBStatusIsTrue(@Param("roleIds") List<Long> roleIds);
}