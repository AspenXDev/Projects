package com.library.lms.repository;

import com.library.lms.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Integer> {
    List<Fine> findByLoan_LoanId(Integer loanId);
    List<Fine> findByPaid(Boolean paid);
    List<Fine> findByLoan_Member_MemberId(Integer memberId);

    // Unpaid fines by member, then derive at frontend for totalunpaidfines etc.
    List<Fine> findByLoan_Member_MemberIdAndPaidFalse(Integer memberId);
}
