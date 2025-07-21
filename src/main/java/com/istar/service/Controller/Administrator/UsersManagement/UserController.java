package com.istar.service.Controller.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.User;
import com.istar.service.Service.Administrator.UsersManagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "${app.cors.allowed-origins}")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('USER_ADD')")
    @PostMapping
    public User registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setBStatus(true);
        return userService.saveUser(user);
    }

    @PreAuthorize("hasAuthority('USER_VIEWED')")
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/status")
    public Map<String, String> getStatusLabels() {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("A", "Active");
        statusMap.put("B", "Blocked");
        statusMap.put("C", "Closed");
        return statusMap;
    }

    @PreAuthorize("hasAuthority('USER_EDIT')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAuthority('USER_EDIT')")
    @PutMapping("/{id}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable("id") Long id) {
        userService.resetPassword(id);
        return ResponseEntity.ok("Password has been reset.");
    }

    @PreAuthorize("hasAuthority('USER_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok("User has been soft-deleted.");
    }

    @PreAuthorize("hasAuthority('USER_VIEWED')")
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) String keyword) {
        return userService.searchUsers(keyword);
    }
}
