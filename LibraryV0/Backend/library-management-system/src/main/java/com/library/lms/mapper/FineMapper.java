// FineMapper.java
package com.library.lms.mapper;

import com.library.lms.dto.FineDTO;
import com.library.lms.model.Fine;
import com.library.lms.model.Loan;

import java.time.LocalDateTime;

/**
 * Mapper for converting between {@link Fine} entity and {@link FineDTO} record.
 */
public class FineMapper {

    /**
     * Converts a {@link Fine} entity to a {@link FineDTO} record.
     *
     * @param fine The Fine entity to convert.
     * @return The corresponding FineDTO record, or null if the entity is null.
     */
    public static FineDTO toDTO(Fine fine) {
        if (fine == null) {
            return null;
        }
        return new FineDTO(
                fine.getFineId(),
                fine.getLoan() != null ? fine.getLoan().getLoanId() : null,
                fine.getAmount(),
                fine.getPaid(),
                fine.getCreatedAt(),
                fine.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link FineDTO} record to a new {@link Fine} entity.
     * Note: This method does not set the associated Loan entity.
     * The Loan entity should be set in the service layer after this conversion by fetching it from a repository.
     *
     * @param dto The FineDTO record to convert.
     * @return A new Fine entity, or null if the DTO is null.
     */
    public static Fine toEntity(FineDTO dto) {
        if (dto == null) {
            return null;
        }
        Fine fine = new Fine();
        fine.setFineId(dto.fineId()); // fineId might be null for new entities
        fine.setAmount(dto.amount());
        fine.setPaid(dto.paid());
        // Loan entity must be set by the service layer
        // Timestamps createdAt and updatedAt are typically set by the database
        return fine;
    }

    /**
     * Updates an existing {@link Fine} entity with data from a {@link FineDTO} record.
     *
     * @param fine The existing Fine entity to update.
     * @param dto The FineDTO record containing the updated data.
     * @return The updated Fine entity.
     */
    public static Fine updateEntityFromDTO(Fine fine, FineDTO dto) {
        if (fine == null || dto == null) {
            return fine;
        }
        fine.setAmount(dto.amount());
        fine.setPaid(dto.paid());
        // loanId and timestamps are typically not updated directly
        return fine;
    }

    /**
     * Utility method to associate a Loan with an existing Fine entity.
     *
     * @param fine The Fine entity to update.
     * @param loan The Loan entity to set.
     * @return The updated Fine entity.
     */
    public static Fine associateLoan(Fine fine, Loan loan) {
        if (fine == null) return null;
        fine.setLoan(loan);
        return fine;
    }
}