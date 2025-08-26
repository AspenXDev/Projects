// LoanDTO.java
package com.library.lms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Direct mapping to the 'loans' table.
public record LoanDTO(
    Integer loanId,
    Integer memberId, // Foreign key to members table
    Integer bookId, // Foreign key to books table
    LocalDate loanDate,
    LocalDate dueDate,
    LocalDate returnDate, // Can be null
    Integer renewCount,
    String status, // Maps to ENUM('Active', 'Returned')
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}