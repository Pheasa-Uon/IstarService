package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Repository.Administrator.UsersManagement.RoleFeaturePermissionRepository;
import com.istar.service.Repository.Administrator.UsersManagement.RoleRepository;
import com.istar.service.Service.Administrator.UsersManagement.RoleFeaturePermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    private RoleFeaturePermissionService roleFeaturePermissionService;
    private RoleFeaturePermissionRepository roleFeaturePermissionRepository;

    public List<Role> getAllRoles() {

        //return roleRepository.findAll();
        return roleRepository.findBybStatusTrue();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        if (role.getRolesCode() == null || role.getRolesCode().isEmpty()) {
            String maxRolesrCode = roleRepository.findMaxUserCode();
            int nextCode = 1;
            if (maxRolesrCode != null) {
                nextCode = Integer.parseInt(maxRolesrCode) + 1;
            }
            role.setRolesCode(String.format("%05d", nextCode));
        }
        System.out.println("Role bStatus before setting: " + role.getBStatus());
        if (role.getBStatus() == null) {
            role.setBStatus(true);
            System.out.println("Role bStatus set to true by default");
        }
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        return roleRepository.save(role);
    }

//    @Transactional
//    public Role createRoleWithPermissions(Role role, List<RoleFeaturePermission> permissions) {
//        // Auto-generate role code if missing
//        if (role.getRolesCode() == null || role.getRolesCode().isEmpty()) {
//            String maxRolesrCode = roleRepository.findMaxUserCode();
//            int nextCode = (maxRolesrCode != null) ? Integer.parseInt(maxRolesrCode) + 1 : 1;
//            role.setRolesCode(String.format("%05d", nextCode));
//        }
//
//        // Set default bStatus
//        if (role.getBStatus() == null) {
//            role.setBStatus(true);
//        }
//
//        role.setCreatedAt(LocalDateTime.now());
//        role.setUpdatedAt(LocalDateTime.now());
//
//        Role savedRole = roleRepository.save(role);
//
//        // Attach roleId to each permission and save
//        for (RoleFeaturePermission permission : permissions) {
//            permission.setRole(savedRole);
//            permission.setCreatedAt(LocalDateTime.now());
//            permission.setUpdatedAt(LocalDateTime.now());
//            permissionRepository.save(permission);
//        }
//
//        return savedRole;
//    }

    public Role createRoleWithPermissions(Role role, List<RoleFeaturePermission> permissions) {
        Role savedRole = createRole(role);

        for (RoleFeaturePermission permission : permissions) {
            permission.setRole(savedRole);
            permission.setId(savedRole.getId());
            roleFeaturePermissionService.createPermission(permission); // <-- Use correct service
        }

        return savedRole;
    }


    public Role updateRole(Long id, Role updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setName(updatedRole.getName());
                    role.setRolesCode(updatedRole.getRolesCode());
                    role.setRolesStatus(updatedRole.getRolesStatus());
                    role.setBStatus(updatedRole.getBStatus());
                    role.setDescription(updatedRole.getDescription());
                    role.setUpdatedAt(LocalDateTime.now());
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with ID " + id));
    }

    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found" + id));

        role.setBStatus(false); // soft delete flag
        role.setUpdatedAt(LocalDateTime.now());
        roleRepository.save(role);
    }

    public List<Role> searchRoles(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return roleRepository.findAll().stream()
                    .filter(Role::isBStatus) // or correct getter method here
                    .toList();
        }
        return roleRepository.searchActiveRolesByKeyword(keyword);
    }

}

