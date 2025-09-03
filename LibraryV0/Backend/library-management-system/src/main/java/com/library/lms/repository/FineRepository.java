// path: Backend/src/main/java/com/library/lms/repository/FineRepository.java
package com.library.lms.repository;

import com.library.lms.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Integer> {
    List<Fine> findByLoan_LoanId(Integer loanId);
    List<Fine> findByPaid(Boolean paid);
    List<Fine> findByLoan_Member_MemberId(Integer memberId);

    // Used by FineServiceImpl
    List<Fine> findByLoan_Member_MemberIdAndPaidFalse(Integer memberId);

    // For dashboard unpaid sum
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM Fine f WHERE f.paid = false")
    Double sumUnpaidAmounts();
}
