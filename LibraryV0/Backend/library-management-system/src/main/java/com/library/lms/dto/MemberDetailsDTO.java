// MemberDetailsDTO.java
package com.library.lms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Composite DTO representing a Member with associated User and Role details.
 * Useful for displaying member profiles or lists in the frontend.
 */
public record MemberDetailsDTO(
    Integer memberId,
    // --- Member-specific fields ---
    String fullName,
    LocalDate registrationDate,
    LocalDate membershipValidUntil,
    String membershipStatus, // Maps to ENUM('Active', 'Expired')
    // --- User-specific fields (from 'users' table) ---
    Integer userId,
    String username,
    String email,
    Boolean isActive,
    // --- Role-specific field (from 'roles' table via 'users') ---
    String roleName,
    // --- Timestamps (from 'members' table for clarity in composite DTO) ---
    LocalDateTime memberCreatedAt,
    LocalDateTime memberUpdatedAt
) {}