package com.library.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "members")
@NamedEntityGraph(
	    name = "Member.loansAndReservations",
	    attributeNodes = {
	        @NamedAttributeNode("loans"),
	        @NamedAttributeNode("reservations")
	    }
	)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnoreProperties({"password"}) // avoid exposing sensitive fields
    private User user;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "membership_valid_until", nullable = false)
    private LocalDate membershipValidUntil;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_status", nullable = false)
    private MembershipStatus membershipStatus = MembershipStatus.Active;

    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false, nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"member"})
    private Set<Loan> loans;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"member"})
    private Set<Reservation> reservations;

    public enum MembershipStatus { Active, Expired }

    // Getters and Setters
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDate getMembershipValidUntil() {
		return membershipValidUntil;
	}

	public void setMembershipValidUntil(LocalDate membershipValidUntil) {
		this.membershipValidUntil = membershipValidUntil;
	}

	public MembershipStatus getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(MembershipStatus membershipStatus) {
		this.membershipStatus = membershipStatus;
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

    // Getters and Setters

}
