package com.library.lms.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Fine {
    private Integer fineId;
    private Loan loan;
    private BigDecimal amount;
    private Boolean paid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Fine() {}
    public Fine(Integer fineId, Loan loan, BigDecimal amount, Boolean paid) {
        this.fineId = fineId;
        this.loan = loan;
        this.amount = amount;
        this.paid = paid;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // getters and setters
    public Integer getFineId() { return fineId; }
    public void setFineId(Integer fineId) { this.fineId = fineId; }
    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Boolean getPaid() { return paid; }
    public void setPaid(Boolean paid) { this.paid = paid; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
