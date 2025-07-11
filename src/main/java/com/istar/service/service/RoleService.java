package com.istar.service.service;

import com.istar.service.model.Role;
import com.istar.service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
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

        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
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

}

