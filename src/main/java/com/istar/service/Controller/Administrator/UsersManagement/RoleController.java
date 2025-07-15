package com.istar.service.Controller.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Repository.Administrator.UsersManagement.FeatureRepository;
import com.istar.service.Repository.Administrator.UsersManagement.RoleFeaturePermissionRepository;
import com.istar.service.Repository.Administrator.UsersManagement.RoleRepository;
import com.istar.service.Service.Administrator.UsersManagement.FeatureService;
import com.istar.service.Service.Administrator.UsersManagement.RoleService;
import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Entity.Administrator.UsersManagment.Feature;
import com.istar.service.dto.Administrator.UsersManagement.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "${app.cors.allowed-origins}")
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private RoleFeaturePermissionRepository roleFeaturePermissionRepository;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto) {
        roleService.createRoleWithPermissions(roleDto);
        return ResponseEntity.ok("Role created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        return ResponseEntity.ok(roleService.updateRole(id, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public Map<String, String> getStatusLabels() {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("A", "Active");
        statusMap.put("B", "Blocked");
        statusMap.put("C", "Closed");
        return statusMap;
    }

    @GetMapping("/search")
    public List<Role> searchRoles(@RequestParam(required = false) String keyword) {
        System.out.println(keyword);
        return roleService.searchRoles(keyword);
    }

//    @PostMapping("/roles/with-permissions")
//    public Role createRoleWithPermissions(Role role, List<RoleFeaturePermission> permissions) {
//        Role savedRole = roleRepository.save(role);
//        for (RoleFeaturePermission perm : permissions) {
//            perm.setRole(savedRole);
//            perm.setCreatedAt(LocalDateTime.now());
//            perm.setUpdatedAt(LocalDateTime.now());
//        }
//        roleFeaturePermissionRepository.saveAll(permissions);
//        return savedRole;
//    }

}
