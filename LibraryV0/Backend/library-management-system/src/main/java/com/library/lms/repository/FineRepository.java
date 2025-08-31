package com.library.lms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.lms.model.Fine;

@Repository
public interface FineRepository extends JpaRepository<Fine, Integer> {
    List<Fine> findByLoan_LoanId(Integer loanId);
    List<Fine> findByLoan_Member_MemberId(Integer memberId);
    List<Fine> findByPaid(Boolean paid);
}
