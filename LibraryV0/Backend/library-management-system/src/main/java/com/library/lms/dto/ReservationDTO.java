// ReservationDTO.java
package com.library.lms.dto;

import java.time.LocalDateTime;

/**
 * A Data Transfer Object for representing Reservation information.
 * This record is used for data exchange between the service layer and the controllers/client.
 */
public record ReservationDTO(
    Integer reservationId,
    Integer memberId, // ID of the associated member
    Integer bookId,   // ID of the associated book
    LocalDateTime reservationDate,
    LocalDateTime holdUntil,
    String status,    // Represents ReservationStatus enum as String
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
