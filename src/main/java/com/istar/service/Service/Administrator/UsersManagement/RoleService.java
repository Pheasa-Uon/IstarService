package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Repository.Administrator.UsersManagement.RoleFeaturePermissionRepository;
import com.istar.service.dto.Administrator.UsersManagement.RoleDto;
import com.istar.service.Entity.Administrator.UsersManagment.Feature;
import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Repository.Administrator.UsersManagement.FeatureRepository;
import com.istar.service.Repository.Administrator.UsersManagement.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final FeatureRepository featureRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, FeatureRepository featureRepository) {
        this.roleRepository = roleRepository;
        this.featureRepository = featureRepository;
    }

    @Autowired
    private RoleFeaturePermissionService roleFeaturePermissionService;
    @Autowired
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

    public Role createRoleWithPermissions(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setRolesCode(roleDto.getRolesCode());
        role.setRolesStatus(roleDto.getRolesStatus());
        role.setCreatedAt(LocalDateTime.now());
        role.setBStatus(true);

        // Load features
        List<Feature> selectedFeatures = featureRepository.findAllById(roleDto.getFeatureIds());
        Set<Feature> featureSet = new HashSet<>(selectedFeatures);
        role.setFeatures(featureSet); // assuming Role has a Set<Feature> features

        return roleRepository.save(role);
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

