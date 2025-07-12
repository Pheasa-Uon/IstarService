package com.istar.service.Service.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.User;
import java.util.List;

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
