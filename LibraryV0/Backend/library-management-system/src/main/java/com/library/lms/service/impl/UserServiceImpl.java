// PATH: src/main/java/com/library/lms/service/impl/UserServiceImpl.java
package com.library.lms.service.impl;

import com.library.lms.model.Role;
import com.library.lms.model.User;
import com.library.lms.repository.RoleRepository;
import com.library.lms.repository.UserRepository;
import com.library.lms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        if (!StringUtils.hasText(user.getUsername())) throw new IllegalArgumentException("Username is required");
        if (!StringUtils.hasText(user.getPasswordHash())) throw new IllegalArgumentException("Password is required");

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer userId, User user) {
        User existing = getUserById(userId);

        if (StringUtils.hasText(user.getUsername())) existing.setUsername(user.getUsername());
        if (StringUtils.hasText(user.getEmail())) existing.setEmail(user.getEmail());
        if (StringUtils.hasText(user.getPasswordHash()))
            existing.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        if (user.getRole() != null) existing.setRole(user.getRole());
        if (user.getIsActive() != null) existing.setIsActive(user.getIsActive());

        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = getUserByUsername(username);
        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            return user;
        }
        throw new IllegalArgumentException("Invalid credentials");
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }
}
