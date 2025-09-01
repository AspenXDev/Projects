package com.library.lms.service.impl;

import com.library.lms.exception.FineNotFoundException;
import com.library.lms.model.Fine;
import com.library.lms.repository.FineRepository;
import com.library.lms.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineRepository fineRepository;

    @Override
    public Fine createFine(Fine fine) {
        return fineRepository.save(fine);
    }

    @Override
    public Fine getFineById(Integer fineId) {
        return fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException("Fine not found: " + fineId));
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
        return fineRepository.save(fine);
    }

    @Override
    public void deleteFine(Integer fineId) {
        if (!fineRepository.existsById(fineId)) {
            throw new FineNotFoundException("Fine not found: " + fineId);
        }
        fineRepository.deleteById(fineId);
    }

    @Override
    public List<Fine> getFinesByMemberId(Integer memberId) {
        return fineRepository.findAllByLoan_Member_MemberId(memberId);
    }

    @Override
    public List<Fine> getFinesByLoanId(Integer loanId) {
        return fineRepository.findAllByLoan_LoanId(loanId);
    }

    @Override
    public List<Fine> getFinesByPaidStatus(Boolean paid) {
        return fineRepository.findAllByPaid(paid);
    }
}
