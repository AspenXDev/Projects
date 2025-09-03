// path: Backend/src/main/java/com/library/lms/repository/LoanRepository.java
package com.library.lms.repository;

import com.library.lms.model.Loan;
import com.library.lms.model.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    // Find loans by member ID
    List<Loan> findByMember_MemberId(Integer memberId);

    // Find loans by book ID
    List<Loan> findByBook_BookId(Integer bookId);

    // Find loans by loan status
    List<Loan> findByStatus(LoanStatus status);

    // Count loans by status (active, returned, overdue)
    long countByStatus(LoanStatus status);

    // Count overdue loans
    long countByStatusAndDueDateBefore(LoanStatus status, LocalDate dueDate);

    // Find loans for a specific user ID
    List<Loan> findByMemberUserUserIdAndStatus(Integer userId, LoanStatus status);

    // Optional: find overdue loans for a specific user
    List<Loan> findByMemberUserUserIdAndStatusAndDueDateBefore(Integer userId, LoanStatus status, LocalDate date);
}
