package com.library.lms.repository;

import com.library.lms.model.Loan;
import com.library.lms.model.Loan.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByStatus(LoanStatus status);
    List<Loan> findByMemberMemberId(Integer memberId);
    List<Loan> findByBookBookId(Integer bookId);
}
