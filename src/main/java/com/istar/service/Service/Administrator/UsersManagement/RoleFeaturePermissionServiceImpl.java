package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Feature;
import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Repository.Administrator.UsersManagement.FeatureRepository;
import com.istar.service.Repository.Administrator.UsersManagement.RoleFeaturePermissionRepository;
import com.istar.service.Repository.Administrator.UsersManagement.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleFeaturePermissionServiceImpl implements RoleFeaturePermissionService {

    @Autowired
    private RoleFeaturePermissionRepository permissionRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleFeaturePermission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public List<RoleFeaturePermission> getPermissionsByRole(Long roleId) {
        return permissionRepository.findByRoleId(roleId);
    }

    @Override
    public RoleFeaturePermission createPermission(RoleFeaturePermission permission) {
        permission.setCreatedAt(LocalDateTime.now());
        permission.setUpdatedAt(LocalDateTime.now());
        return permissionRepository.save(permission);
    }

    @Override
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

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    public void createPermissionsBulk(List<RoleFeaturePermission> permissions) {
        if (!permissions.isEmpty()) {
            Long roleId = permissions.get(0).getRole().getId();
            permissionRepository.deleteByRoleId(roleId);
        }
        permissionRepository.saveAll(permissions);
    }

    public void createPermissionsForNewRole(Role role) {
        List<Feature> features = featureRepository.findAll();
        List<RoleFeaturePermission> permissions = new ArrayList<>();

        for (Feature feature : features) {
            RoleFeaturePermission perm = new RoleFeaturePermission();
            perm.setRole(role);
            perm.setFeature(feature);
            perm.setIsSearch(feature.getIsSearch());
            perm.setIsAdd(feature.getIsAdd());
            perm.setIsViewed(feature.getIsViewed());
            perm.setIsEdit(feature.getIsEdit());
            perm.setIsApprove(feature.getIsApprove());
            perm.setIsReject(feature.getIsReject());
            perm.setIsDeleted(feature.getIsDeleted());
            perm.setIsSave(feature.getIsSave());
            perm.setIsClear(feature.getIsClear());
            perm.setIsCancel(feature.getIsCancel());
            perm.setIsProcess(feature.getIsProcess());
            perm.setIsImport(feature.getIsImport());
            perm.setIsExport(feature.getIsExport());
            perm.setBStatus(true);
            perm.setCreatedAt(LocalDateTime.now());
            perm.setUpdatedAt(LocalDateTime.now());

            permissions.add(perm);
        }

        permissionRepository.saveAll(permissions);
    }
}
