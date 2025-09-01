package com.library.lms.service;

import java.util.List;
import com.library.lms.model.Loan;
import com.library.lms.model.enums.LoanStatus;

public interface LoanService {
    Loan borrowBook(Integer memberId, Integer bookId);
    Loan returnBook(Integer loanId);
    Loan renewLoan(Integer loanId);
    List<Loan> getLoansByMemberId(Integer memberId);
    List<Loan> getLoansByBookId(Integer bookId);
    List<Loan> getLoansByStatus(LoanStatus status);
}
