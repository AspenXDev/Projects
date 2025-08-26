// FineDTO.java
package com.library.lms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Direct mapping to the 'fines' table.
public record FineDTO(
    Integer fineId,
    Integer loanId, // Foreign key to loans table
    BigDecimal amount,
    Boolean paid,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}