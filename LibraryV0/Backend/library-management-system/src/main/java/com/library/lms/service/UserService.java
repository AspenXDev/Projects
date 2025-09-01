// PATH: src/main/java/com/library/lms/service/UserService.java
package com.library.lms.service;

import com.library.lms.model.Role;
import com.library.lms.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User getUserById(Integer userId);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(Integer userId, User user);

    void deleteUser(Integer userId);

    User authenticate(String username, String password);

    // fetch role by name
    Role getRoleByName(String roleName);
}
