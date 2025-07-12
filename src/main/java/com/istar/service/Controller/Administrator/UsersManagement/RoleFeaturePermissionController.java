package com.istar.service.Controller.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Service.Administrator.UsersManagement.RoleFeaturePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class RoleFeaturePermissionController {

    @Autowired
    private RoleFeaturePermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<RoleFeaturePermission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<RoleFeaturePermission>> getPermissionsByRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(permissionService.getPermissionsByRole(roleId));
    }

    @PostMapping
    public ResponseEntity<RoleFeaturePermission> createPermission(@RequestBody RoleFeaturePermission permission) {
        return ResponseEntity.ok(permissionService.createPermission(permission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleFeaturePermission> updatePermission(
            @PathVariable Long id,
            @RequestBody RoleFeaturePermission permission) {
        return ResponseEntity.ok(permissionService.updatePermission(id, permission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok().build();
    }
}
