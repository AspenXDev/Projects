package com.library.lms.dto;

import java.time.LocalDate;

public record ReservationDTO(
    Long id,
    Long bookId,
    Long memberId,
    LocalDate reservationDate,
    String status
) {}
