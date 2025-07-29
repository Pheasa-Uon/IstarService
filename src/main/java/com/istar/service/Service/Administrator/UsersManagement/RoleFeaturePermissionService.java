package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Feature;
import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Repository.Administrator.UsersManagement.FeatureRepository;
import com.istar.service.Repository.Administrator.UsersManagement.RoleFeaturePermissionRepository;
import com.istar.service.Repository.Administrator.UsersManagement.RoleRepository;
import com.istar.service.dto.Administrator.UsersManagement.FeaturePermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleFeaturePermissionService {

    @Autowired
    private RoleFeaturePermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private RoleFeaturePermissionRepository roleFeaturePermissionRepository;

    public List<RoleFeaturePermission> getAllPermissions() {
        return permissionRepository.findAll();
    }

//    public List<RoleFeaturePermission> getPermissionsByRole(Long roleId) {
//        return permissionRepository.findByRoleId(roleId);
//    }

    public List<FeaturePermissionDTO> getPermissionsByRole(Role role) {
        List<RoleFeaturePermission> perms = roleFeaturePermissionRepository.findByRole(role);

        return perms.stream().map(p -> {
            FeaturePermissionDTO dto = new FeaturePermissionDTO();
            dto.setRoleId(p.getRole().getId());
            dto.setFeatureId(p.getFeature().getId());
            dto.setIsSearch(p.getIsSearch());
            dto.setIsAdd(p.getIsAdd());
            dto.setIsViewed(p.getIsViewed());
            dto.setIsEdit(p.getIsEdit());
            dto.setIsApprove(p.getIsApprove());
            dto.setIsReject(p.getIsReject());
            dto.setIsDeleted(p.getIsDeleted());
            dto.setIsSave(p.getIsSave());
            dto.setIsClear(p.getIsClear());
            dto.setIsCancel(p.getIsCancel());
            dto.setIsProcess(p.getIsProcess());
            dto.setIsImport(p.getIsImport());
            dto.setIsExport(p.getIsExport());
            dto.setBStatus(p.getBStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    public RoleFeaturePermission createPermission(RoleFeaturePermission permission) {
        permission.setCreatedAt(LocalDateTime.now());
        permission.setUpdatedAt(LocalDateTime.now());
        return permissionRepository.save(permission);
    }

    public RoleFeaturePermission updatePermission(Long id, RoleFeaturePermission updated) {
        return permissionRepository.findById(id)
                .map(permission -> {
                    copyPermissionFields(permission, updated);
                    permission.setUpdatedAt(LocalDateTime.now());
                    return permissionRepository.save(permission);
                })
                .orElseThrow(() -> new RuntimeException("Permission not found with ID " + id));
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    // NEW method to bulk save/update permissions
    public void savePermissionsBulk(List<FeaturePermissionDTO> dtos) {
        for (FeaturePermissionDTO dto : dtos) {
            if (dto.getRoleId() == null || dto.getFeatureId() == null) {
                throw new IllegalArgumentException("Role ID and Feature ID must not be null");
            }

            Role role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + dto.getRoleId()));

            Feature feature = featureRepository.findById(dto.getFeatureId())
                    .orElseThrow(() -> new RuntimeException("Feature not found: " + dto.getFeatureId()));

            Optional<RoleFeaturePermission> existingOpt = permissionRepository.findByRoleIdAndFeatureId(role.getId(), feature.getId());

            RoleFeaturePermission permission = existingOpt.orElse(new RoleFeaturePermission());
            permission.setRole(role);
            permission.setFeature(feature);

            permission.setIsSearch(dto.getIsSearch());
            permission.setIsAdd(dto.getIsAdd());
            permission.setIsViewed(dto.getIsViewed());
            permission.setIsEdit(dto.getIsEdit());
            permission.setIsApprove(dto.getIsApprove());
            permission.setIsReject(dto.getIsReject());
            permission.setIsDeleted(dto.getIsDeleted());
            permission.setIsSave(dto.getIsSave());
            permission.setIsClear(dto.getIsClear());
            permission.setIsCancel(dto.getIsCancel());
            permission.setIsProcess(dto.getIsProcess());
            permission.setIsImport(dto.getIsImport());
            permission.setIsExport(dto.getIsExport());
            permission.setBStatus(dto.getBStatus());

            if (permission.getId() == null) {
                permission.setCreatedAt(LocalDateTime.now());
            }
            permission.setUpdatedAt(LocalDateTime.now());

            permissionRepository.save(permission);
        }
    }


    private void copyPermissionFields(RoleFeaturePermission target, RoleFeaturePermission source) {
        target.setIsSearch(source.getIsSearch());
        target.setIsAdd(source.getIsAdd());
        target.setIsViewed(source.getIsViewed());
        target.setIsEdit(source.getIsEdit());
        target.setIsApprove(source.getIsApprove());
        target.setIsReject(source.getIsReject());
        target.setIsDeleted(source.getIsDeleted());
        target.setIsSave(source.getIsSave());
        target.setIsClear(source.getIsClear());
        target.setIsCancel(source.getIsCancel());
        target.setIsProcess(source.getIsProcess());
        target.setIsImport(source.getIsImport());
        target.setIsExport(source.getIsExport());
        target.setBStatus(source.getBStatus());
    }

}
