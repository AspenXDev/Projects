package com.library.lms.service;

import com.library.lms.model.Fine;
import java.util.List;

public interface FineService {
    Fine createFine(Fine fine);
    Fine getFineById(Integer fineId);
    List<Fine> getFinesByLoanId(Integer loanId);
    List<Fine> getUnpaidFinesByMemberId(Integer memberId);
    Fine updateFine(Fine fine);
}
