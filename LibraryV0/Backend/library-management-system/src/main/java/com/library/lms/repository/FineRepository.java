package com.library.lms.repository;

import com.library.lms.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Integer> {
    List<Fine> findAllByLoan_Member_MemberId(Integer memberId);
    List<Fine> findAllByPaid(Boolean paid);
    List<Fine> findAllByLoan_LoanId(Integer loanId);
}
