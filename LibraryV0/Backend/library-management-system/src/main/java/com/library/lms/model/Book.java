package com.library.lms.model;

import com.library.lms.model.enums.BookStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    private String title;
    private String author;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @Column(name = "published_year")
    private Integer publishedYear;

    private String category;

    @Column(name = "total_copies")
    private Integer totalCopies;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "location_section")
    private String locationSection;

    @Column(name = "location_shelf")
    private Integer locationShelf;

    @Column(name = "location_row")
    private Integer locationRow;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    // ======================
    // Constructors
    // ======================
    public Book() {}

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = 1;
        this.availableCopies = 1;
        this.status = BookStatus.Available;
    }

    public Book(String title, String author, String isbn, BookStatus status) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
        this.totalCopies = 1;
        this.availableCopies = 1;
    }

    // ======================
    // Getters and Setters
    // ======================
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

    public BookStatus getStatus() { return status; }
    public void setStatus(BookStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    @Override
    public String toString() {
        return String.format(
            "Book[id=%d, title='%s', author='%s', isbn='%s', status=%s]",
            bookId, title, author, isbn, status
        );
    }
}
