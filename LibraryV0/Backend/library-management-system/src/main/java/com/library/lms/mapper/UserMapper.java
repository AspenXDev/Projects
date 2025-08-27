// UserMapper.java
package com.library.lms.mapper;

// import com.library.lms.dto.UserDTO;
// import com.library.lms.model.Role;
// import com.library.lms.model.User;
import com.library.lms.dto.*;
import com.library.lms.model.*;

import java.time.LocalDateTime;

/**
 * Mapper for converting between {@link User} entity and {@link UserDTO} record.
 */
public class UserMapper {

    /**
     * Converts a {@link User} entity to a {@link UserDTO} record.
     * This method correctly maps all fields, including the role ID and timestamps.
     *
     * @param user The User entity to convert.
     * @return The corresponding UserDTO record, or null if the entity is null.
     */
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getRole() != null ? user.getRole().getRoleId() : null, // Correctly map roleId
                user.getIsActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link UserDTO} record to a {@link User} entity.
     * This method is typically used when creating a new User.
     * Note: For setting the {@link Role}, it's generally better to
     * fetch the actual {@link Role} entity from the database using the {@code roleId}
     * within a service layer, rather than creating a partial {@link Role} object here.
     *
     * @param dto The UserDTO record to convert.
     * @return The corresponding User entity, or null if the DTO is null.
     */
    public static User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setUserId(dto.userId()); // userId might be null for new entities
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPasswordHash(dto.passwordHash());
        user.setIsActive(dto.isActive());
        // For role: In a real application, you'd typically fetch the Role entity by ID
        // from a repository here or in the service layer.
        // For simplicity in the mapper, we create a Role with just the ID.
        if (dto.roleId() != null) {
            Role role = new Role();
            role.setRoleId(dto.roleId());
            user.setRole(role);
        }
        // CreatedAt and UpdatedAt are typically set by the database or a listener,
        // so they are not set directly from DTO on entity creation.
        // If you need to explicitly set them for an update, you'd do it differently.
        return user;
    }

    /**
     * Updates an existing {@link User} entity with data from a {@link UserDTO} record.
     *
     * @param user The existing User entity to update.
     * @param dto The UserDTO record containing the updated data.
     * @return The updated User entity.
     */
    public static User updateEntityFromDTO(User user, UserDTO dto) {
        if (user == null || dto == null) {
            return user;
        }
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPasswordHash(dto.passwordHash()); // Be cautious with password updates
        user.setIsActive(dto.isActive());
        // Role update logic: typically involves fetching the new Role entity if roleId changes
        if (dto.roleId() != null && (user.getRole() == null || !dto.roleId().equals(user.getRole().getRoleId()))) {
            Role role = new Role(); // Placeholder, would be fetched from repository
            role.setRoleId(dto.roleId());
            user.setRole(role);
        }
        // createdAt is usually not updated
        // updatedAt is usually handled by the database
        return user;
    }
}