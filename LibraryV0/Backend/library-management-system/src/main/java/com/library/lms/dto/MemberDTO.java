package com.library.lms.dto;

import java.time.LocalDate;

public class MemberDTO {
    private Long memberId;
    private Long userId;
    private String fullName;
    private LocalDate registrationDate;
    private LocalDate membershipValidUntil;
    private String membershipStatus; // 'Active' or 'Expired'

    public MemberDTO() {}

    public MemberDTO(Long memberId, Long userId, String fullName,
                     LocalDate registrationDate, LocalDate membershipValidUntil,
                     String membershipStatus) {
        this.memberId = memberId;
        this.userId = userId;
        this.fullName = fullName;
        this.registrationDate = registrationDate;
        this.membershipValidUntil = membershipValidUntil;
        this.membershipStatus = membershipStatus;
    }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public LocalDate getMembershipValidUntil() { return membershipValidUntil; }
    public void setMembershipValidUntil(LocalDate membershipValidUntil) { this.membershipValidUntil = membershipValidUntil; }

    public String getMembershipStatus() { return membershipStatus; }
    public void setMembershipStatus(String membershipStatus) { this.membershipStatus = membershipStatus; }
}
