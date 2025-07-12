package com.istar.service.Controller.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Entity.Administrator.UsersManagment.UserRole;
import com.istar.service.Service.Administrator.UsersManagement.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@PreAuthorize("hasRole('ADMIN')")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    // DTO for role assignment request
    public static class RoleAssignmentRequest {
        public Long userId;
        public Long roleId;
    }

    @PostMapping("/assign")
    public ResponseEntity<UserRole> assignRole(@RequestBody RoleAssignmentRequest request) {
        UserRole userRole = userRoleService.assignRoleToUser(request.userId, request.roleId);
        return ResponseEntity.ok(userRole);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeRole(@RequestBody RoleAssignmentRequest request) {
        userRoleService.removeRoleFromUser(request.userId, request.roleId);
        return ResponseEntity.ok("Role removed from user");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable Long userId) {
        List<Role> roles = userRoleService.getUserRoles(userId);
        return ResponseEntity.ok(roles);
    }
}
