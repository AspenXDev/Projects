package com.library.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.lms.model.Fine;

public interface FineRepository extends JpaRepository<Fine, Integer> {

    // Find all fines for a specific loan
    List<Fine> findByLoan_LoanId(Integer loanId);

    // Find all fines for a specific member (through loan -> member)
    List<Fine> findByLoan_Member_MemberId(Integer memberId);

    // Find fines by paid status
    List<Fine> findByPaid(Boolean paid);
}
