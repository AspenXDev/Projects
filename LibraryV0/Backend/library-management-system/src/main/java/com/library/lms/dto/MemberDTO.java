package com.library.lms.dto;

import java.time.LocalDate;

public record MemberDTO(
    Long id,
    String fullName,
    String email,
    String phone,
    LocalDate membershipStart,
    LocalDate membershipEnd,
    String membershipStatus
) {}
