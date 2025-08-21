package com.library.lms.service;

import java.util.List;

import com.library.lms.model.User;

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
