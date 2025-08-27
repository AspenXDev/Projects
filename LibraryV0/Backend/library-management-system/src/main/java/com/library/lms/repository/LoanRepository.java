package com.library.lms.repository;

import com.library.lms.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByMemberId(Integer memberId);
    List<Loan> findByMemberIdAndStatus(Integer memberId, String status);
}
