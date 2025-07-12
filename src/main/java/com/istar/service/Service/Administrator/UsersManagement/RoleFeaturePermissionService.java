package com.istar.service.Service.Administrator.UsersManagement;
import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import java.util.List;


public interface RoleFeaturePermissionService {

    List<RoleFeaturePermission> getAllPermissions();
    List<RoleFeaturePermission> getPermissionsByRole(Long roleId);
    RoleFeaturePermission createPermission(RoleFeaturePermission permission);
    RoleFeaturePermission updatePermission(Long id, RoleFeaturePermission permission);
    void deletePermission(Long id);

}
