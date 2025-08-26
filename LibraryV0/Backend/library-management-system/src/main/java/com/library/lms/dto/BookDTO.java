// BookDTO.java
package com.library.lms.dto;

import java.time.LocalDateTime;

// Direct mapping to the 'books' table.
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
    Integer locationShelf,
    Integer locationRow,
    String status, // Maps to ENUM('Available', 'Borrowed','Reserved')
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}