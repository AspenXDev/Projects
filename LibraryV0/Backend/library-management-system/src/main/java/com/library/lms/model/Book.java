package com.library.lms.model;

import java.time.LocalDateTime;

public class Book {
    private Integer bookId;
    private String title;
    private String author;
    private String isbn;
    private Integer publishedYear;
    private String category;
    private Integer totalCopies;
    private Integer availableCopies;
    private String locationSection;
    private Integer locationShelf;
    private Integer locationRow;
    private String status; // Available, Borrowed, Reserved
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Book() {}
    public Book(Integer bookId, String title, String author, String isbn, Integer publishedYear, String category, Integer totalCopies, Integer availableCopies, String locationSection, Integer locationShelf, Integer locationRow, String status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.locationSection = locationSection;
        this.locationShelf = locationShelf;
        this.locationRow = locationRow;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // getters and setters
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getTotalCopies() { return totalCopies; }
    public void setTotalCopies(Integer totalCopies) { this.totalCopies = totalCopies; }
    public Integer getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(Integer availableCopies) { this.availableCopies = availableCopies; }
    public String getLocationSection() { return locationSection; }
    public void setLocationSection(String locationSection) { this.locationSection = locationSection; }
    public Integer getLocationShelf() { return locationShelf; }
    public void setLocationShelf(Integer locationShelf) { this.locationShelf = locationShelf; }
    public Integer getLocationRow() { return locationRow; }
    public void setLocationRow(Integer locationRow) { this.locationRow = locationRow; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
