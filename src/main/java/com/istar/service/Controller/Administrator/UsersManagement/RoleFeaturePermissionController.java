package com.istar.service.Controller.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Repository.Administrator.UsersManagement.RoleRepository;
import com.istar.service.Service.Administrator.UsersManagement.RoleFeaturePermissionService;
import com.istar.service.dto.Administrator.UsersManagement.FeaturePermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class RoleFeaturePermissionController {

    @Autowired
    private RoleFeaturePermissionService permissionService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<RoleFeaturePermission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<FeaturePermissionDTO>> getPermissionsByRole(@PathVariable Long roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        List<FeaturePermissionDTO> permissions = permissionService.getPermissionsByRole(role);
        return ResponseEntity.ok(permissions);
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

    @PostMapping("/bulk")
    public ResponseEntity<?> savePermissionsBulk(@RequestBody List<FeaturePermissionDTO> dtos) {
        permissionService.savePermissionsBulk(dtos);
        return ResponseEntity.ok().build();
    }
}
