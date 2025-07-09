package com.istar.service.service;

import com.istar.service.model.User;
import com.istar.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
