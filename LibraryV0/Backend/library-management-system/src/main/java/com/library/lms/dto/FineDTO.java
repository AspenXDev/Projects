// FineDTO.java
package com.library.lms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A Data Transfer Object for representing Fine information.
 * This record is used for data exchange between the service layer and the controllers/client.
 */
public record FineDTO(
    Integer fineId,
    Integer loanId, // ID of the associated loan
    BigDecimal amount,
    Boolean paid,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
