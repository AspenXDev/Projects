// BookDTO.java
package com.library.lms.dto;

import java.time.LocalDateTime;

/**
 * A Data Transfer Object for representing Book information.
 * This record is used for data exchange between the service layer and the controllers/client.
 */
public record BookDTO(
    Integer bookId,
    String title,
    String author,
    String isbn,
    Integer publishedYear,
    String category,
    Integer totalCopies,
    Integer availableCopies,
    String locationSection,
    String locationShelf,
    Integer locationRow,
    String status, // Represents BookStatus enum as String
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}