// path: src/main/java/com/library/lms/dto/MemberResponseDTO.java
package com.library.lms.dto;

import java.math.BigDecimal;

public class MemberResponseDTO {

    private String username;
    private String fullName;
    private String membershipStatus;
    private String membershipValidUntil;
    private int activeLoans;
    private int overdueLoans;
    private BigDecimal totalUnpaidFines;

    // Default constructor
    public MemberResponseDTO() {}

    // Constructor matching class name
    public MemberResponseDTO(String username, String fullName, String membershipStatus,
                             String membershipValidUntil, int activeLoans, int overdueLoans,
                             BigDecimal totalUnpaidFines) {
        this.username = username;
        this.fullName = fullName;
        this.membershipStatus = membershipStatus;
        this.membershipValidUntil = membershipValidUntil;
        this.activeLoans = activeLoans;
        this.overdueLoans = overdueLoans;
        this.totalUnpaidFines = totalUnpaidFines;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getMembershipStatus() { return membershipStatus; }
    public void setMembershipStatus(String membershipStatus) { this.membershipStatus = membershipStatus; }

    public String getMembershipValidUntil() { return membershipValidUntil; }
    public void setMembershipValidUntil(String membershipValidUntil) { this.membershipValidUntil = membershipValidUntil; }

    public int getActiveLoans() { return activeLoans; }
    public void setActiveLoans(int activeLoans) { this.activeLoans = activeLoans; }

    public int getOverdueLoans() { return overdueLoans; }
    public void setOverdueLoans(int overdueLoans) { this.overdueLoans = overdueLoans; }

    public BigDecimal getTotalUnpaidFines() { return totalUnpaidFines; }
    public void setTotalUnpaidFines(BigDecimal totalUnpaidFines) { this.totalUnpaidFines = totalUnpaidFines; }
}
