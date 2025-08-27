package com.library.lms.service;

import com.library.lms.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Integer userId);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Integer userId);
}
