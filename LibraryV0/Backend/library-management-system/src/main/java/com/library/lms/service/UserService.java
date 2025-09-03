package com.library.lms.service;

import com.library.lms.model.Role;
import com.library.lms.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Integer userId);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(Integer userId, User user);

    void deleteUser(Integer userId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User authenticate(String username, String password);

    Role getRoleByName(String roleName);
}
