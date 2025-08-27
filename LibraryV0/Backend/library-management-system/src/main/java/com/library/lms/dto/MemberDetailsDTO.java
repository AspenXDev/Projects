package com.library.lms.dto;

import com.library.lms.model.enums.MembershipStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberDetailsDTO {
    private Long memberId;
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private MembershipStatus membershipStatus;
    private LocalDate registrationDate;
    private LocalDate membershipValidUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberDetailsDTO() {}

    public MemberDetailsDTO(Long memberId, Long userId, String username, String email,
                            String fullName, MembershipStatus membershipStatus,
                            LocalDate registrationDate, LocalDate membershipValidUntil,
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.memberId = memberId;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.membershipStatus = membershipStatus;
        this.registrationDate = registrationDate;
        this.membershipValidUntil = membershipValidUntil;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public MembershipStatus getMembershipStatus() { return membershipStatus; }
    public void setMembershipStatus(MembershipStatus membershipStatus) { this.membershipStatus = membershipStatus; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public LocalDate getMembershipValidUntil() { return membershipValidUntil; }
    public void setMembershipValidUntil(LocalDate membershipValidUntil) { this.membershipValidUntil = membershipValidUntil; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
