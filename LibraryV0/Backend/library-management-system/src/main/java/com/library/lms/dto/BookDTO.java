package com.library.lms.dto;

public record BookDTO(
    Long id,
    String title,
    String author,
    String isbn,
    boolean available
) {}
