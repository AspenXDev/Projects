// LibrarianPureDTO.java
package com.library.lms.dto;

import java.time.LocalDateTime;

public record LibrarianPureDTO(
    Integer librarianId,
    Integer userId,
    String fullName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}