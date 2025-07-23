package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Entity.Administrator.UsersManagment.User;
import com.istar.service.Entity.Administrator.UsersManagment.UserRole;
import com.istar.service.Repository.Administrator.UsersManagement.RoleRepository;
import com.istar.service.Repository.Administrator.UsersManagement.UserRepository;
import com.istar.service.Repository.Administrator.UsersManagement.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole assignRoleToUser(Long userId, Long roleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Check if role already assigned
        UserRole existing = userRoleRepository.findByUserIdAndRoleId(userId, roleId).orElse(null);

        if (existing != null) {
            // Toggle bStatus (true <-> false)
            existing.setbStatus(!Boolean.TRUE.equals(existing.getbStatus()));
            existing.setUpdatedAt(LocalDateTime.now());
            return userRoleRepository.save(existing);
        }

        // Assign new role
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setbStatus(true); // New roles are active by default
        userRole.setCreatedAt(LocalDateTime.now());
        userRole.setUpdatedAt(LocalDateTime.now());

        return userRoleRepository.save(userRole);
    }


    public void removeRoleFromUser(Long userId, Long roleId) {
        UserRole userRole = userRoleRepository.findByUserIdAndRoleId(userId, roleId)
                .orElseThrow(() -> new RuntimeException("UserRole not found for userId " + userId + " and roleId " + roleId));
        if (Boolean.TRUE.equals(userRole.getbStatus())) {
            userRole.setbStatus(false);
            userRole.setUpdatedAt(LocalDateTime.now());
            userRoleRepository.save(userRole);
        }
    }

    public List<UserRole> getUserRoles(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }
}
