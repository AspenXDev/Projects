package com.library.lms.service;

import com.library.lms.model.Loan;
import java.util.List;

public interface LoanService {
    Loan createLoan(Loan loan);
    Loan getLoanById(Integer loanId);
    List<Loan> getAllLoans();
    Loan updateLoan(Integer loanId, Loan loanDetails);
    void deleteLoan(Integer loanId);

    // Convenience methods
    List<Loan> getLoansByMemberId(Integer memberId);
    List<Loan> getLoansByBookId(Integer bookId);
    List<Loan> getLoansByStatus(Loan.LoanStatus status);
}
