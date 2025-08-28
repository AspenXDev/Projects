package com.library.lms.service;

import com.library.lms.model.User;

import java.util.List;

public interface UserService {

    // ---------- Authentication ----------
    User authenticate(String username, String password);

    // ---------- CRUD ----------
    User createUser(User user);
    User updateUser(Integer userId, User user);
    void deleteUser(Integer userId);
    User getUserById(Integer userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();
}
