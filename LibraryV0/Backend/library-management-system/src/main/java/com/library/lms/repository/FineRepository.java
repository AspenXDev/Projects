package com.library.lms.repository;

import com.library.lms.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Integer> {
    List<Fine> findByPaid(Boolean paid);
    List<Fine> findByLoanMemberMemberId(Integer memberId);
    List<Fine> findByLoanLoanId(Integer loanId);
}
