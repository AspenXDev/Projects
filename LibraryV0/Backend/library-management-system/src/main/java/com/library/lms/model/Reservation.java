package com.library.lms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import com.library.lms.model.converters.ReservationStatusConverter;
import com.library.lms.model.enums.ReservationStatus;

@Entity
@Table(name = "reservations")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reservationId")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer reservationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    @JsonIgnoreProperties({"loans", "reservations"})
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnoreProperties({"loans", "reservations"})
    private Book book;

    @Column(name = "reservation_date", insertable = false, updatable = false, nullable = false)
    private LocalDateTime reservationDate;

    @Column(name = "hold_until")
    private LocalDateTime holdUntil;

    @Convert(converter = ReservationStatusConverter.class)
    @Column(name = "status", nullable = false)
    private ReservationStatus status = ReservationStatus.Waiting;

    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false, nullable = false)
    private LocalDateTime updatedAt;

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
    
    // Getters and Setters
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalDateTime getHoldUntil() {
		return holdUntil;
	}

	public void setHoldUntil(LocalDateTime holdUntil) {
		this.holdUntil = holdUntil;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
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
}
