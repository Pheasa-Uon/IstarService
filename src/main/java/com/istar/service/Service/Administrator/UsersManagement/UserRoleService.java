package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Entity.Administrator.UsersManagment.User;
import com.istar.service.Entity.Administrator.UsersManagment.UserRole;
import com.istar.service.Repository.Administrator.UsersManagement.RoleRepository;
import com.istar.service.Repository.Administrator.UsersManagement.UserRepository;
import com.istar.service.Repository.Administrator.UsersManagement.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

//    public UserRole assignRoleToUser(Long userId, Long roleId) {
//        // Check if role already assigned to the user
//        Optional<UserRole> existing = userRoleRepository.findByUserIdAndRoleId(userId, roleId);
//
//        if (existing.isPresent()) {
//            UserRole userRole = existing.get();
//            if (!Boolean.TRUE.equals(userRole.getbStatus())) {
//                // Reactivate the role assignment
//                userRole.setbStatus(true);
//                userRole.setUpdatedAt(LocalDateTime.now());
//                return userRoleRepository.save(userRole);
//            }
//            System.out.println("Role already assigned to user " + userId + " - " + roleId + " - " + userRole.getbStatus());
//            return userRole; // Already active, just return
//        }
//
//        // Assign new role if not exists
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Role role = roleRepository.findById(roleId)
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//
//        UserRole newUserRole = new UserRole();
//        newUserRole.setUser(user);
//        newUserRole.setRole(role);
//        newUserRole.setbStatus(true);
//        newUserRole.setCreatedAt(LocalDateTime.now());
//        newUserRole.setUpdatedAt(LocalDateTime.now());
//
//        System.out.println("Assigning role Id " + roleId + " to user Id " + userId);
//        return userRoleRepository.save(newUserRole);
//    }

    public UserRole assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Optional<UserRole> existing = userRoleRepository.findByUserAndRole(user, role);

        if (existing.isPresent()) {
            UserRole userRole = existing.get();
            if (!Boolean.TRUE.equals(userRole.getbStatus())) {
                userRole.setbStatus(true);
                userRole.setUpdatedAt(LocalDateTime.now());
                return userRoleRepository.save(userRole); // Reactivated
            }

            System.out.println("Role already active for user " + userId);
            return userRole; // Already active
        }

        // If not exists, create new one
        UserRole newUserRole = new UserRole();
        newUserRole.setUser(user);
        newUserRole.setRole(role);
        newUserRole.setbStatus(true);
        newUserRole.setCreatedAt(LocalDateTime.now());
        newUserRole.setUpdatedAt(LocalDateTime.now());


        return userRoleRepository.save(newUserRole);
    }

    public void removeRoleFromUser(Long userId, Long roleId) {
        UserRole userRole = userRoleRepository.findByUserIdAndRoleId(userId, roleId)
                .orElseThrow(() -> new RuntimeException("User role mapping not found"));

        userRole.setbStatus(false);
        userRoleRepository.save(userRole);
    }

    public List<Role> getUserRoles(Long userId) {
        return userRoleRepository.findByUserIdAndBStatusTrue(userId).stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }

}
