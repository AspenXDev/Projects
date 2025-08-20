package com.library.lms.dto;

import java.time.LocalDate;

public record LoanDTO(
    Long id,
    Long bookId,
    Long memberId,
    LocalDate loanDate,
    LocalDate dueDate,
    String status
) {}
