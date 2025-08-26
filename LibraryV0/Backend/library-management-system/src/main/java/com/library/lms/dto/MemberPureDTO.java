// MemberPureDTO.java
package com.library.lms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberPureDTO(
    Integer memberId,
    Integer userId,
    String fullName,
    LocalDate registrationDate,
    LocalDate membershipValidUntil,
    String membershipStatus, // ENUM 'Active', 'Expired' as String
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}