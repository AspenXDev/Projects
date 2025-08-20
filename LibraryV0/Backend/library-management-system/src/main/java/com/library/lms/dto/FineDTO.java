package com.library.lms.dto;

import java.time.LocalDate;

public record FineDTO(
    Long id,
    Long memberId,
    Double amount,
    String reason,
    LocalDate fineDate,
    boolean paid
) {}
