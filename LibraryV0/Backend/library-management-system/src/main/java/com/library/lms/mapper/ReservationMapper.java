// ReservationMapper.java
package com.library.lms.mapper;

import com.library.lms.dto.ReservationDTO;
import com.library.lms.model.Book;
import com.library.lms.model.Member;
import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;

import java.time.LocalDateTime;

/**
 * Mapper for converting between {@link Reservation} entity and {@link ReservationDTO} record.
 */
public class ReservationMapper {

    /**
     * Converts a {@link Reservation} entity to a {@link ReservationDTO} record.
     *
     * @param reservation The Reservation entity to convert.
     * @return The corresponding ReservationDTO record, or null if the entity is null.
     */
    public static ReservationDTO toDTO(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        return new ReservationDTO(
                reservation.getReservationId(),
                reservation.getMember() != null ? reservation.getMember().getMemberId() : null,
                reservation.getBook() != null ? reservation.getBook().getBookId() : null,
                reservation.getReservationDate(),
                reservation.getHoldUntil(),
                reservation.getStatus() != null ? reservation.getStatus().name() : null, // Convert enum to String
                reservation.getCreatedAt(),
                reservation.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link ReservationDTO} record to a new {@link Reservation} entity.
     * Note: This method does not set the associated Member and Book entities.
     * These should be set in the service layer after this conversion by fetching them from repositories.
     *
     * @param dto The ReservationDTO record to convert.
     * @return A new Reservation entity, or null if the DTO is null.
     */
    public static Reservation toEntity(ReservationDTO dto) {
        if (dto == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setReservationId(dto.reservationId()); // reservationId might be null for new entities
        reservation.setReservationDate(dto.reservationDate());
        reservation.setHoldUntil(dto.holdUntil());
        if (dto.status() != null) {
            // Assuming ReservationStatus enum exists
            reservation.setStatus(ReservationStatus.valueOf(dto.status())); // Convert String to enum
        }
        // Member and Book entities must be set by the service layer
        // Timestamps createdAt and updatedAt are typically set by the database
        return reservation;
    }

    /**
     * Updates an existing {@link Reservation} entity with data from a {@link ReservationDTO} record.
     *
     * @param reservation The existing Reservation entity to update.
     * @param dto The ReservationDTO record containing the updated data.
     * @return The updated Reservation entity.
     */
    public static Reservation updateEntityFromDTO(Reservation reservation, ReservationDTO dto) {
        if (reservation == null || dto == null) {
            return reservation;
        }
        reservation.setHoldUntil(dto.holdUntil());
        if (dto.status() != null) {
            reservation.setStatus(ReservationStatus.valueOf(dto.status()));
        }
        // memberId, bookId, reservationDate, and timestamps are typically not updated directly
        return reservation;
    }

    /**
     * Utility method to associate Member and Book with an existing Reservation entity.
     *
     * @param reservation The Reservation entity to update.
     * @param member The Member entity to set.
     * @param book The Book entity to set.
     * @return The updated Reservation entity.
     */
    public static Reservation associateMemberAndBook(Reservation reservation, Member member, Book book) {
        if (reservation == null) return null;
        reservation.setMember(member);
        reservation.setBook(book);
        return reservation;
    }
}