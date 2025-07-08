package com.istar.service.service;

import com.istar.service.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findByUsername(String username);
    List<User> getAllUsers();
}
