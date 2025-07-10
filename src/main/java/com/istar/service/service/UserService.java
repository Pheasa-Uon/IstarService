package com.istar.service.service;

import com.istar.service.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    User updateUser(Long id, User user);
    User findByUsername(String username);
    List<User> getAllUsers();
    User getUserById(Long id);
    User resetPassword(Long id);
    List<User> searchUsers(String keyword);

    void softDeleteUser(Long id);
}
