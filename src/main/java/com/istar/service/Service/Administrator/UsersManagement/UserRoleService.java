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
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole assignRoleToUser(Long userId, Long roleId) {
        if (userRoleRepository.existsByUserIdAndRoleId(userId, roleId)) {
            throw new RuntimeException("User already has this role");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setCreatedAt(LocalDateTime.now());
        userRole.setUpdatedAt(LocalDateTime.now());
        userRole.setbStatus(true);

        return userRoleRepository.save(userRole);
    }

    public void removeRoleFromUser(Long userId, Long roleId) {
        UserRole userRole = userRoleRepository.findByUserIdAndRoleId(userId, roleId)
                .orElseThrow(() -> new RuntimeException("User role mapping not found"));

        userRole.setbStatus(false);
        userRoleRepository.save(userRole);
    }

    public List<UserRole> getUserRoles(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }
}
