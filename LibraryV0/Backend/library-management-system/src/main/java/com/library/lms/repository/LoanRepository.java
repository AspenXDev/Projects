package com.library.lms.repository;

import com.library.lms.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    // Find all loans for a specific member
    List<Loan> findByMember_MemberId(Integer memberId);

    // Find all loans for a specific book
    List<Loan> findByBook_BookId(Integer bookId);

    // Find loans by status (Active, Returned)
    List<Loan> findByStatus(Loan.LoanStatus status);
}
