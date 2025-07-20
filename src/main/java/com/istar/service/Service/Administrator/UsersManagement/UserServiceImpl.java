package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import com.istar.service.Entity.Administrator.UsersManagment.User;
import com.istar.service.dto.Administrator.UsersManagement.FeaturePermissionDTO;
import com.istar.service.Repository.Administrator.UsersManagement.RoleFeaturePermissionRepository;
import com.istar.service.Repository.Administrator.UsersManagement.UserRepository;
import com.istar.service.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User saveUser(User user) {
        if (user.getUserCode() == null || user.getUserCode().isEmpty()) {
            String maxUserCode = userRepository.findMaxUserCode();
            int nextCode = 1;
            if (maxUserCode != null) {
                nextCode = Integer.parseInt(maxUserCode) + 1;
            }
            user.setUserCode(String.format("%05d", nextCode));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User updateUser(Long id, User existingUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setUsername(existingUser.getUsername());
            user.setEmail(existingUser.getEmail());
            user.setName(existingUser.getName());

            // Only update password if it's not null or empty
//            if (existingUser.getPassword() != null && !existingUser.getPassword().isEmpty()) {
//                user.setPassword(existingUser.getPassword());
//            }
            if (existingUser.getPassword() != null
                    && !existingUser.getPassword().isEmpty()
                    && !existingUser.getPassword().equals("$2a$10$")) {
                user.setPassword(existingUser.getPassword());
            }else {
                user.setPassword(passwordEncoder.encode(existingUser.getPassword()));
            }

            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }


    @Override
    public List<User> getAllUsers() {
        //return userRepository.findAll(); // ❌ returns soft-deleted users too
        return userRepository.findBybStatusTrue(); // ✅ only active users
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public User resetPassword(Long id) {
        User user = getUserById(id);
        user.setPassword(passwordEncoder.encode("123456")); // default password
        return userRepository.save(user);
    }

    public void softDeleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBStatus(false);  // Soft delete
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    public List<User> searchUsers(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return userRepository.findAll().stream()
                    .filter(User::isBStatus) // or correct getter method here
                    .toList();
        }
        return userRepository.searchActiveUsersByKeyword(keyword);
    }
}
