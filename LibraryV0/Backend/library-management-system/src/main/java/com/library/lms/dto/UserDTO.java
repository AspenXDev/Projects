// UserDTO.java
package com.library.lms.dto;

import java.time.LocalDateTime;

public record UserDTO(
    Integer userId,
    String username,
    String email,
    String passwordHash,
    Integer roleId,
    Boolean isActive, // Maps to 'is_active'
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}