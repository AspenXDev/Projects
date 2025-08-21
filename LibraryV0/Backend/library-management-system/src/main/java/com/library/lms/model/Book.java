package com.library.lms.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import com.library.lms.model.enums.BookStatus;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 150, nullable = false)
    private String author;

    @Column(length = 13, nullable = false, unique = true)
    private String isbn;

    @Column(name = "published_year")
    private Integer publishedYear;

    @Column(length = 50)
    private String category;

    @Column(name = "total_copies", nullable = false)
    private Integer totalCopies = 1;

    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies = 1;

    @Column(name = "location_section", length = 50)
    private String locationSection;

    @Column(name = "location_shelf")
    private Integer locationShelf;

    @Column(name = "location_row")
    private Integer locationRow;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status = BookStatus.Available;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Loan> loans;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    // ======================
    // Constructors
    // ======================
    public Book() {}

    // Optional? Add a constructor with required fields
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(Integer publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(Integer totalCopies) {
		this.totalCopies = totalCopies;
	}

	public Integer getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(Integer availableCopies) {
		this.availableCopies = availableCopies;
	}

	public String getLocationSection() {
		return locationSection;
	}

	public void setLocationSection(String locationSection) {
		this.locationSection = locationSection;
	}

	public Integer getLocationShelf() {
		return locationShelf;
	}

	public void setLocationShelf(Integer locationShelf) {
		this.locationShelf = locationShelf;
	}

	public Integer getLocationRow() {
		return locationRow;
	}

	public void setLocationRow(Integer locationRow) {
		this.locationRow = locationRow;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

    // ======================
    // Getters & Setters
    // ======================
}
