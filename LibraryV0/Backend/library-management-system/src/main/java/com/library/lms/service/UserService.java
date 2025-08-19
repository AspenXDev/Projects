package com.library.lms.service;

import com.library.lms.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Integer userId);
    List<User> getAllUsers();
    User updateUser(Integer userId, User userDetails);
    void deleteUser(Integer userId);

    // Convenience
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
