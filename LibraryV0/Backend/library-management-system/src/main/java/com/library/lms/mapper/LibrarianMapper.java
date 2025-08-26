// LibrarianMapper.java
package com.library.lms.mapper;

import com.library.lms.dto.*;
//import com.library.lms.dto.LibrarianDetailsDTO;
//import com.library.lms.dto.LibrarianPureDTO;
import com.library.lms.model.Librarian;
import com.library.lms.model.User;

import java.time.LocalDateTime;

/**
 * Mapper for converting between {@link Librarian} entity and {@link LibrarianPureDTO} / {@link LibrarianDetailsDTO} records.
 */
public class LibrarianMapper {

    /**
     * Converts a {@link Librarian} entity to a {@link LibrarianPureDTO} record.
     * This DTO directly mirrors the 'librarians' table.
     *
     * @param librarian The Librarian entity to convert.
     * @return The corresponding LibrarianPureDTO record, or null if the entity is null.
     */
    public static LibrarianPureDTO toDTO(Librarian librarian) {
        if (librarian == null) {
            return null;
        }
        return new LibrarianPureDTO(
                librarian.getLibrarianId(),
                librarian.getUser() != null ? librarian.getUser().getUserId() : null,
                librarian.getFullName(),
                librarian.getCreatedAt(),
                librarian.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link Librarian} entity to a {@link LibrarianDetailsDTO} record.
     * This DTO combines Librarian, User, and Role information.
     *
     * @param librarian The Librarian entity to convert.
     * @return The corresponding LibrarianDetailsDTO record, or null if the entity is null.
     */
    public static LibrarianDetailsDTO toDetailsDTO(Librarian librarian) {
        if (librarian == null) {
            return null;
        }
        User user = librarian.getUser();
        return new LibrarianDetailsDTO(
                librarian.getLibrarianId(),
                // Librarian-specific fields
                librarian.getFullName(),
                // User-specific fields
                user != null ? user.getUserId() : null,
                user != null ? user.getUsername() : null,
                user != null ? user.getEmail() : null,
                user != null ? user.getIsActive() : null,
                // Role-specific field
                user != null && user.getRole() != null ? user.getRole().getRoleName() : null,
                // Timestamps
                librarian.getCreatedAt(),
                librarian.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link LibrarianPureDTO} record to a new {@link Librarian} entity.
     * Note: This method does not set the associated User entity. Use {@link #toEntity(LibrarianPureDTO, User)}
     * or set the User in the service layer after this conversion.
     *
     * @param dto The LibrarianPureDTO record to convert.
     * @return A new Librarian entity, or null if the DTO is null.
     */
    public static Librarian toEntity(LibrarianPureDTO dto) {
        if (dto == null) {
            return null;
        }
        Librarian librarian = new Librarian();
        librarian.setLibrarianId(dto.librarianId()); // librarianId might be null for new entities
        librarian.setFullName(dto.fullName());
        // Timestamps createdAt and updatedAt are typically set by the database
        return librarian;
    }

    /**
     * Converts a {@link LibrarianPureDTO} record to a new {@link Librarian} entity and associates it with an existing {@link User}.
     *
     * @param dto The LibrarianPureDTO record to convert.
     * @param user The existing User entity to associate with the Librarian.
     * @return A new Librarian entity with the associated User, or null if the DTO is null.
     * @throws IllegalArgumentException if the User is null.
     */
    public static Librarian toEntity(LibrarianPureDTO dto, User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null when creating a Librarian entity.");
        Librarian librarian = toEntity(dto); // Use the base conversion
        if (librarian != null) {
            librarian.setUser(user);
        }
        return librarian;
    }

    /**
     * Updates an existing {@link Librarian} entity with data from a {@link LibrarianPureDTO} record.
     *
     * @param librarian The existing Librarian entity to update.
     * @param dto The LibrarianPureDTO record containing the updated data.
     * @return The updated Librarian entity.
     */
    public static Librarian updateEntityFromDTO(Librarian librarian, LibrarianPureDTO dto) {
        if (librarian == null || dto == null) {
            return librarian;
        }
        librarian.setFullName(dto.fullName());
        // userId and timestamps are typically not updated directly through a DTO update.
        // If userId needs to change, it's usually a more complex operation involving user management.
        return librarian;
    }
}