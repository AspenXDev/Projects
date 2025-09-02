package com.library.lms.service;

import com.library.lms.model.Role;
import com.library.lms.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Create (hashes password)
    User createUser(User user);

    // Existence checks
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    // Getters
    User getUserById(Integer userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();

    // Update / Delete
    User updateUser(Integer userId, User user);
    void deleteUser(Integer userId);

    // Auth
    User authenticate(String username, String password);

    // Roles
    Role getRoleByName(String roleName);

    // Kept for compatibility with existing callers
    User saveUser(User user);
    Optional<User> findByUsername(String username);
}
