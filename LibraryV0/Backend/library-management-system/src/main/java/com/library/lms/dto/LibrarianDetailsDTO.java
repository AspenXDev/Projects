// LibrarianDetailsDTO.java
package com.library.lms.dto;

import java.time.LocalDateTime;

/**
 * Composite DTO representing a Librarian with associated User and Role details.
 * Useful for displaying librarian profiles or lists in the frontend.
 */
public record LibrarianDetailsDTO(
    Integer librarianId,
    // --- Librarian-specific fields ---
    String fullName,
    // --- User-specific fields (from 'users' table) ---
    Integer userId,
    String username,
    String email,
    Boolean isActive,
    // --- Role-specific field (from 'roles' table via 'users') ---
    String roleName,
    // --- Timestamps (from 'librarians' table for clarity in composite DTO) ---
    LocalDateTime librarianCreatedAt,
    LocalDateTime librarianUpdatedAt
) {}