package com.library.lms.service;

import com.library.lms.model.Loan;
import java.util.List;

public interface LoanService {
    Loan createLoan(Integer memberId, Integer bookId);
    Loan returnLoan(Integer loanId);
    Loan renewLoan(Integer loanId);
    Loan getLoanById(Integer loanId);
    List<Loan> getLoansByMemberId(Integer memberId);
    List<Loan> getActiveLoansByMemberId(Integer memberId);
}
