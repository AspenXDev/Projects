package com.library.lms.model;

import java.time.LocalDate;

public class Member {
    private Integer memberId;
    private User user;
    private String fullName;
    private LocalDate registrationDate;
    private LocalDate membershipValidUntil;
    private String membershipStatus; // Active / Expired
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Member() {}
    public Member(Integer memberId, User user, String fullName, LocalDate registrationDate, LocalDate membershipValidUntil, String membershipStatus) {
        this.memberId = memberId;
        this.user = user;
        this.fullName = fullName;
        this.registrationDate = registrationDate;
        this.membershipValidUntil = membershipValidUntil;
        this.membershipStatus = membershipStatus;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // getters and setters
    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public LocalDate getMembershipValidUntil() { return membershipValidUntil; }
    public void setMembershipValidUntil(LocalDate membershipValidUntil) { this.membershipValidUntil = membershipValidUntil; }
    public String getMembershipStatus() { return membershipStatus; }
    public void setMembershipStatus(String membershipStatus) { this.membershipStatus = membershipStatus; }
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }
}
