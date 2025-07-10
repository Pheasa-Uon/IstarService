package com.istar.service.controller;

import com.istar.service.model.User;
import com.istar.service.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "${app.cors.allowed-origins}")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setBStatus(true);  // Set your new boolean field here

        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/status")
    public Map<String, String> getStatusLabels() {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("A", "Active");
        statusMap.put("B", "Blocked");
        statusMap.put("C", "Closed");
        return statusMap;
    }

    // Update user
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/api/users/{id}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable("id") Long id) {
        userService.resetPassword(id);
        return ResponseEntity.ok("Password has been reset.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok("User has been soft-deleted.");
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) String keyword) {
        System.out.println(keyword);
        return userService.searchUsers(keyword);
    }

}
