package com.istar.service.Service.Administrator.UsersManagement.Permission;

import com.istar.service.dto.Administrator.UsersManagement.FeaturePermissionDTO;
import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Repository.Administrator.UsersManagement.RoleFeaturePermissionRepository;
import com.istar.service.Repository.Administrator.UsersManagement.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final UserRoleRepository userRoleRepository;
    private final RoleFeaturePermissionRepository roleFeaturePermissionRepository;

    public List<FeaturePermissionDTO> getMergedPermissionsByUserId(Long userId) {
        List<Long> roleIds = userRoleRepository.findByUserIdAndBStatusTrue(userId).stream()
                .map(userRole -> userRole.getRole().getId())
                .collect(Collectors.toList());

        if (roleIds.isEmpty()) return Collections.emptyList();

        List<RoleFeaturePermission> permissions = roleFeaturePermissionRepository
                .findByRoleIdInAndBStatusIsTrue(roleIds);

        Map<String, FeaturePermissionDTO> merged = new HashMap<>();

        for (RoleFeaturePermission p : permissions) {
            String code = p.getFeature().getCode();

            FeaturePermissionDTO dto = merged.computeIfAbsent(code, k -> {
                FeaturePermissionDTO f = new FeaturePermissionDTO();
                f.setFeatureCode(code);
                return f;
            });

            dto.setIsAdd(dto.getIsAdd() || Boolean.TRUE.equals(p.getIsAdd()));
            dto.setIsEdit(dto.getIsEdit() || Boolean.TRUE.equals(p.getIsEdit()));
            dto.setIsViewed(dto.getIsViewed() || Boolean.TRUE.equals(p.getIsViewed()));
            dto.setIsDeleted(dto.getIsDeleted() || Boolean.TRUE.equals(p.getIsDeleted()));
            dto.setIsSave(dto.getIsSave() || Boolean.TRUE.equals(p.getIsSave()));
            dto.setIsCancel(dto.getIsCancel() || Boolean.TRUE.equals(p.getIsCancel()));
        }

        return new ArrayList<>(merged.values());
    }
}
