package com.library.lms.service.impl;

import com.library.lms.model.Fine;
import com.library.lms.repository.FineRepository;
import com.library.lms.service.FineService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;

    public FineServiceImpl(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    @Override
    public Fine createFine(Fine fine) {
        return fineRepository.save(fine);
    }

    @Override
    public Fine updateFine(Integer fineId, Fine fine) {
        Fine existing = fineRepository.findById(fineId)
                .orElseThrow(() -> new EntityNotFoundException("Fine not found: " + fineId));
        existing.setLoan(fine.getLoan());
        existing.setAmount(fine.getAmount());
        existing.setPaid(fine.getPaid());
        return fineRepository.save(existing);
    }

    @Override
    public void deleteFine(Integer fineId) {
        fineRepository.delete(getFineById(fineId));
    }

    @Override
    @Transactional(readOnly = true)
    public Fine getFineById(Integer fineId) {
        return fineRepository.findById(fineId)
                .orElseThrow(() -> new EntityNotFoundException("Fine not found: " + fineId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fine> getFinesByPaidStatus(Boolean paid) {
        return fineRepository.findByPaid(paid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fine> getFinesByMemberId(Integer memberId) {
        return fineRepository.findByLoanMemberMemberId(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fine> getFinesByLoanId(Integer loanId) {
        return fineRepository.findByLoanLoanId(loanId);
    }
}
