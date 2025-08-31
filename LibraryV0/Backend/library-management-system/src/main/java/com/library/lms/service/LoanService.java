package com.library.lms.service;

import java.util.List;

import com.library.lms.model.Loan;

public interface LoanService {
    // Borrow / Return / Renew
    Loan borrowBook(Integer memberId, Integer bookId);
    Loan returnBook(Integer loanId);
    Loan renewLoan(Integer loanId);

    // Standard CRUD (if needed for admin)
    Loan createLoan(Loan loan);
    Loan getLoanById(Integer loanId);
    List<Loan> getAllLoans();
    Loan updateLoan(Integer loanId, Loan loanDetails);
    void deleteLoan(Integer loanId);

    // Convenience
    List<Loan> getLoansByMemberId(Integer memberId);
    List<Loan> getLoansByBookId(Integer bookId);
    List<Loan> getLoansByStatus(Loan.LoanStatus status);
}
