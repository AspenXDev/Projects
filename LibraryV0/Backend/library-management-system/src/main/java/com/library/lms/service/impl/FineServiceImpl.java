package com.library.lms.service.impl;

import com.library.lms.model.Fine;
import com.library.lms.model.Loan;
import com.library.lms.repository.FineRepository;
import com.library.lms.service.FineService;
import com.library.lms.service.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;
    private final LoanService loanService;

    public FineServiceImpl(FineRepository fineRepository, LoanService loanService) {
        this.fineRepository = fineRepository;
        this.loanService = loanService;
    }

    @Override
    public Fine createFine(Fine fine) {
        // Ensure linked Loan exists
        Integer loanId = fine.getLoan().getLoanId();
        Loan loan = loanService.getLoanById(loanId);
        fine.setLoan(loan);

        return fineRepository.save(fine);
    }

    @Override
    public Fine getFineById(Integer fineId) {
        return fineRepository.findById(fineId)
                .orElseThrow(() -> new RuntimeException("Fine not found with ID: " + fineId));
    }

    @Override
    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    @Override
    public Fine updateFine(Integer fineId, Fine fineDetails) {
        Fine fine = getFineById(fineId);

        fine.setAmount(fineDetails.getAmount());
        fine.setPaid(fineDetails.getPaid());

        if (fineDetails.getLoan() != null) {
            Integer loanId = fineDetails.getLoan().getLoanId();
            Loan loan = loanService.getLoanById(loanId);
            fine.setLoan(loan);
        }

        return fineRepository.save(fine);
    }

    @Override
    public void deleteFine(Integer fineId) {
        Fine fine = getFineById(fineId);
        fineRepository.delete(fine);
    }

    // --- Convenience methods ---
    @Override
    public List<Fine> getFinesByLoanId(Integer loanId) {
        return fineRepository.findByLoan_LoanId(loanId);
    }

    @Override
    public List<Fine> getFinesByMemberId(Integer memberId) {
        return fineRepository.findByLoan_Member_MemberId(memberId);
    }

    @Override
    public List<Fine> getFinesByPaidStatus(Boolean paid) {
        return fineRepository.findByPaid(paid);
    }
}
