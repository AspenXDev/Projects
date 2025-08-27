package com.library.lms.model;

import java.time.LocalDateTime;

public class Reservation {
    private Integer reservationId;
    private Member member;
    private Book book;
    private LocalDateTime reservationDate;
    private LocalDateTime holdUntil;
    private String status; // Waiting, On Hold, Collected, Cancelled
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Reservation() {}
    public Reservation(Integer reservationId, Member member, Book book, String status) {
        this.reservationId = reservationId;
        this.member = member;
        this.book = book;
        this.status = status;
        this.reservationDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // getters and setters
    public Integer getReservationId() { return reservationId; }
    public void setReservationId(Integer reservationId) { this.reservationId = reservationId; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public LocalDateTime getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
    public LocalDateTime getHoldUntil() { return holdUntil; }
    public void setHoldUntil(LocalDateTime holdUntil) { this.holdUntil = holdUntil; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
