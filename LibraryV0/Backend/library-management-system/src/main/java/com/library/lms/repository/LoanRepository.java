package com.library.lms.repository;

import com.library.lms.model.Loan;
import com.library.lms.model.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByMember_MemberId(Integer memberId);
    List<Loan> findByBook_BookId(Integer bookId);
    List<Loan> findByStatus(LoanStatus status);
}