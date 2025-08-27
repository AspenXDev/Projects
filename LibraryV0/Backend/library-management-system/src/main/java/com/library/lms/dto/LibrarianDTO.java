package com.library.lms.dto;

import java.time.LocalDateTime;

public class LibrarianDTO {
    private Long librarianId;
    private Long userId;
    private String fullName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LibrarianDTO() {}

    public LibrarianDTO(Long librarianId, Long userId, String fullName,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.librarianId = librarianId;
        this.userId = userId;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getLibrarianId() { return librarianId; }
    public void setLibrarianId(Long librarianId) { this.librarianId = librarianId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
