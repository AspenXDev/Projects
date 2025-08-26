// ReservationDTO.java
package com.library.lms.dto;

import java.time.LocalDateTime;

// Direct mapping to the 'reservations' table.
public record ReservationDTO(
    Integer reservationId,
    Integer memberId, // Foreign key to members table
    Integer bookId, // Foreign key to books table
    LocalDateTime reservationDate,
    LocalDateTime holdUntil, // Can be null
    String status, // Maps to ENUM('Waiting', 'On Hold', 'Collected', 'Cancelled')
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}