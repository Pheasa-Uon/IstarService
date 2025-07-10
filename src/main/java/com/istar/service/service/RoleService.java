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
        roleRepository.deleteById(id);
    }
}

