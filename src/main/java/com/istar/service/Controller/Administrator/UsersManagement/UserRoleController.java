package com.istar.service.Controller.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.UserRole;
import com.istar.service.Payload.AssignRoleRequest;
import com.istar.service.Service.Administrator.UsersManagement.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/assign")
    public ResponseEntity<UserRole> assignRole(@RequestBody AssignRoleRequest request) {
        return ResponseEntity.ok(userRoleService.assignRoleToUser(request.getUserId(), request.getRoleId()));
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeRole(@RequestBody AssignRoleRequest request) {
        userRoleService.removeRoleFromUser(request.getUserId(), request.getRoleId());
        return ResponseEntity.ok("Role removed from user");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserRole>> getUserRoles(@PathVariable Long userId) {
        return ResponseEntity.ok(userRoleService.getUserRoles(userId));
    }
}
