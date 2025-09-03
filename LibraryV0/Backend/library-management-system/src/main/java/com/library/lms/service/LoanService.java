package com.library.lms.service;

import com.library.lms.model.Loan;
import com.library.lms.model.enums.LoanStatus;

import java.util.List;
import java.util.Optional;

public interface LoanService {

    Loan borrowBook(Integer memberId, Integer bookId);

    Loan returnBook(Integer loanId);

    Loan renewLoan(Integer loanId);

    Optional<Loan> getLoanById(Integer loanId);

    List<Loan> getLoansByMemberId(Integer memberId);

    List<Loan> getLoansByBookId(Integer bookId);

    List<Loan> getLoansByStatus(LoanStatus status);

    List<Loan> getAllLoans();
}
