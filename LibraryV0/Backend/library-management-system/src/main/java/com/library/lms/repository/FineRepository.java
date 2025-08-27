package com.library.lms.repository;

import com.library.lms.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Integer> {

    List<Fine> findByLoanId(Integer loanId);

    List<Fine> findByLoanIdInAndPaidFalse(List<Integer> loanIds);

    List<Fine> findByLoanIdAndPaidFalse(Integer loanId);
}
