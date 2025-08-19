package com.library.lms.service;

import com.library.lms.model.Fine;
import java.util.List;

public interface FineService {
    Fine createFine(Fine fine);
    Fine getFineById(Integer fineId);
    List<Fine> getAllFines();
    Fine updateFine(Integer fineId, Fine fineDetails);
    void deleteFine(Integer fineId);

    // Convenience methods
    List<Fine> getFinesByLoanId(Integer loanId);
    List<Fine> getFinesByMemberId(Integer memberId);
    List<Fine> getFinesByPaidStatus(Boolean paid);
}
