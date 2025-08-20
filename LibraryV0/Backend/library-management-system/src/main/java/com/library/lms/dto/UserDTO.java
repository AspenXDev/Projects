package com.library.lms.dto;

public record UserDTO(
    Long id,
    String username,
    String email,
    String role
) {}
