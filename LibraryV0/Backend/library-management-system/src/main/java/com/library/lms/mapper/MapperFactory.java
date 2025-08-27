// MapperFactory.java
package com.library.lms.mapper;

import com.library.lms.model.*;
import com.library.lms.model.*;
//import com.library.lms.model.Librarian;
//import com.library.lms.model.Member;
//import com.library.lms.model.Role;
//import com.library.lms.model.User;

import java.time.LocalDateTime;

/**
 * Provides factory methods for creating core entities.
 * Note: More complex entity creation logic (like fetching roles from DB) should typically reside in a service layer.
 */
public class MapperFactory {

    /**
     * Creates a new {@link User} entity with basic details.
     * This method assumes a {@code roleId} is provided, and a {@link Role} object is created
     * with just the ID. The actual {@link Role} entity should be fetched from the database
     * and set by the service layer before persisting the {@link User}.
     *
     * @param username The username for the new user.
     * @param email The email for the new user.
     * @param passwordHash The hashed password for the new user.
     * @param roleId The ID of the role for the new user.
     * @return A new User entity.
     */
    public static User createUser(String username, String email, String passwordHash, Integer roleId) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordHash);
        user.setIsActive(true); // Default to active

        if (roleId != null) {
            Role role = new Role();
            role.setRoleId(roleId);
            // In a real application, you'd fetch the Role entity from a repository:
            // Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role not found"));
            user.setRole(role);
        }
        // created_at and updated_at are set by the database
        return user;
    }
}
