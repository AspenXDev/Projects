package com.library.lms.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.library.lms.exception.FineNotFoundException;
import com.library.lms.exception.LoanNotFoundException;
import com.library.lms.model.Fine;
import com.library.lms.model.Loan;
import com.library.lms.repository.FineRepository;
import com.library.lms.service.FineService;
import com.library.lms.service.LoanService;
import jakarta.transaction.Transactional;

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
        if (fine.getLoan() == null || fine.getLoan().getLoanId() == null) {
            throw new LoanNotFoundException("Linked loan is required to create a fine");
        }

        Loan loan = loanService.getLoanById(fine.getLoan().getLoanId());
        fine.setLoan(loan);
        if (fine.getPaid() == null) fine.setPaid(false);

        return fineRepository.save(fine);
    }

    @Override
    public Fine getFineById(Integer fineId) {
        return fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException("Fine with id " + fineId + " not found"));
    }

    @Override
    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    @Override
    public Fine updateFine(Integer fineId, Fine fineDetails) {
        Fine fine = getFineById(fineId);

        if (fineDetails.getAmount() != null)
            fine.setAmount(fineDetails.getAmount());

        fine.setPaid(fineDetails.getPaid() != null ? fineDetails.getPaid() : fine.getPaid());

        if (fineDetails.getLoan() != null && fineDetails.getLoan().getLoanId() != null) {
            Loan loan = loanService.getLoanById(fineDetails.getLoan().getLoanId());
            fine.setLoan(loan);
        }

        return fineRepository.save(fine);
    }

    @Override
    public void deleteFine(Integer fineId) {
        Fine fine = getFineById(fineId);
        fineRepository.delete(fine);
    }

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
