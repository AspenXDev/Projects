// path: Backend/src/main/java/com/library/lms/service/LoanService.java
package com.library.lms.service;

import com.library.lms.model.Loan;
import com.library.lms.model.enums.LoanStatus;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Loan borrowBook(Integer memberId, Integer bookId);  // Batch #3: fill logic
    Loan returnBook(Integer loanId);                    // Batch #3: fill logic
    Loan renewLoan(Integer loanId);                     // Batch #3: fill logic

    Optional<Loan> getLoanById(Integer loanId);
    List<Loan> getLoansByMemberId(Integer memberId);
    List<Loan> getLoansByBookId(Integer bookId);
    List<Loan> getLoansByStatus(LoanStatus status);
    List<Loan> getAllLoans();
    List<Loan> findActiveLoansByUserId(Integer userId);
    List<Loan> findOverdueLoansByUserId(Integer userId);
}
