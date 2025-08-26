// LoanMapper.java
package com.library.lms.mapper;

import com.library.lms.dto.*;
import com.library.lms.model.*;

import java.time.LocalDateTime;

/**
 * Mapper for converting between {@link Loan} entity and {@link LoanDTO} record.
 */
public class LoanMapper {

    /**
     * Converts a {@link Loan} entity to a {@link LoanDTO} record.
     *
     * @param loan The Loan entity to convert.
     * @return The corresponding LoanDTO record, or null if the entity is null.
     */
    public static LoanDTO toDTO(Loan loan) {
        if (loan == null) {
            return null;
        }
        return new LoanDTO(
                loan.getLoanId(),
                loan.getMember() != null ? loan.getMember().getMemberId() : null, // Safely get memberId
                loan.getBook() != null ? loan.getBook().getBookId() : null,     // Safely get bookId
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getRenewCount(),
                loan.getStatus() != null ? loan.getStatus().name() : null, // Convert enum to String
                loan.getCreatedAt(),
                loan.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link LoanDTO} record to a new {@link Loan} entity.
     * Note: This method does not set the associated Member and Book entities directly.
     * These should be set in the service layer after this conversion by fetching them from repositories
     * using the {@code memberId()} and {@code bookId()} from the DTO.
     *
     * @param dto The LoanDTO record to convert.
     * @return A new Loan entity, or null if the DTO is null.
     */
    public static Loan toEntity(LoanDTO dto) {
        if (dto == null) {
            return null;
        }
        Loan loan = new Loan();
        loan.setLoanId(dto.loanId()); // loanId might be null for new entities (database will generate)
        loan.setLoanDate(dto.loanDate());
        loan.setDueDate(dto.dueDate());
        loan.setReturnDate(dto.returnDate());
        loan.setRenewCount(dto.renewCount());
        if (dto.status() != null) {
            loan.setStatus(Loan.LoanStatus.valueOf(dto.status())); // Convert String to enum
        }
        // Member and Book entities must be set by the service layer
        // Timestamps createdAt and updatedAt are typically set by the database automatically
        return loan;
    }

    /**
     * Updates an existing {@link Loan} entity with data from a {@link LoanDTO} record.
     * This method focuses on updating mutable fields of a loan.
     *
     * @param loan The existing Loan entity to update.
     * @param dto The LoanDTO record containing the updated data.
     * @return The updated Loan entity.
     */
    public static Loan updateEntityFromDTO(Loan loan, LoanDTO dto) {
        if (loan == null || dto == null) {
            return loan;
        }
        loan.setLoanDate(dto.loanDate());
        loan.setDueDate(dto.dueDate());
        loan.setReturnDate(dto.returnDate());
        loan.setRenewCount(dto.renewCount());
        if (dto.status() != null) {
            loan.setStatus(Loan.LoanStatus.valueOf(dto.status()));
        }
        // memberId, bookId, and timestamps (createdAt/updatedAt) are typically not updated directly
        // via a simple DTO update to avoid breaking relationships or losing audit data.
        return loan;
    }

    /**
     * Utility method to associate {@link Member} and {@link Book} entities with an existing {@link Loan} entity.
     * This is useful in the service layer after converting a DTO to an entity, when the related
     * entities need to be fetched from their respective repositories and linked.
     *
     * @param loan The Loan entity to update.
     * @param member The Member entity to set. Can be null if association is to be removed or not yet determined.
     * @param book The Book entity to set. Can be null if association is to be removed or not yet determined.
     * @return The updated Loan entity.
     */
    public static Loan associateMemberAndBook(Loan loan, Member member, Book book) {
        if (loan == null) {
            return null;
        }
        loan.setMember(member);
        loan.setBook(book);
        return loan;
    }
}
