package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Repository.Administrator.UsersManagement.RoleFeaturePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleFeaturePermissionService {

    @Autowired
    private RoleFeaturePermissionRepository permissionRepository;

    public List<RoleFeaturePermission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public List<RoleFeaturePermission> getPermissionsByRole(Long roleId) {
        return permissionRepository.findByRoleId(roleId);
    }

    public RoleFeaturePermission createPermission(RoleFeaturePermission permission) {
        permission.setCreatedAt(LocalDateTime.now());
        permission.setUpdatedAt(LocalDateTime.now());
        return permissionRepository.save(permission);
    }

    public RoleFeaturePermission updatePermission(Long id, RoleFeaturePermission updated) {
        return permissionRepository.findById(id)
                .map(permission -> {
                    permission.setIsSearch(updated.getIsSearch());
                    permission.setIsAdd(updated.getIsAdd());
                    permission.setIsViewed(updated.getIsViewed());
                    permission.setIsEdit(updated.getIsEdit());
                    permission.setIsApprove(updated.getIsApprove());
                    permission.setIsReject(updated.getIsReject());
                    permission.setIsDeleted(updated.getIsDeleted());
                    permission.setIsSave(updated.getIsSave());
                    permission.setIsClear(updated.getIsClear());
                    permission.setIsCancel(updated.getIsCancel());
                    permission.setIsProcess(updated.getIsProcess());
                    permission.setIsImport(updated.getIsImport());
                    permission.setIsExport(updated.getIsExport());
                    permission.setBStatus(updated.getBStatus());
                    permission.setUpdatedAt(LocalDateTime.now());
                    return permissionRepository.save(permission);
                })
                .orElseThrow(() -> new RuntimeException("Permission not found with ID " + id));
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
