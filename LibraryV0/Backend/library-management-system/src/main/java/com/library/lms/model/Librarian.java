package com.library.lms.model;

import java.time.LocalDateTime;

public class Librarian {
    private Integer librarianId;
    private User user;
    private String fullName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Librarian() {}
    public Librarian(Integer librarianId, User user, String fullName) {
        this.librarianId = librarianId;
        this.user = user;
        this.fullName = fullName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // getters and setters
    public Integer getLibrarianId() { return librarianId; }
    public void setLibrarianId(Integer librarianId) { this.librarianId = librarianId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
