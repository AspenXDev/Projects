package com.library.lms.service.impl;

import com.library.lms.model.Fine;
import com.library.lms.repository.FineRepository;
import com.library.lms.service.FineService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;

    @Autowired
    public FineServiceImpl(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    @Override
    public Fine createFine(Fine fine) {
        return fineRepository.save(fine);
    }

    @Override
    public Fine getFineById(Integer fineId) {
        Optional<Fine> fine = fineRepository.findById(fineId);
        if (!fine.isPresent()) {
            throw new RuntimeException("Fine not found with ID: " + fineId);
        }
        return fine.get();
    }

    @Override
    public List<Fine> getFinesByLoanId(Integer loanId) {
        return fineRepository.findByLoanId(loanId);
    }

    @Override
    public List<Fine> getUnpaidFinesByMemberId(Integer memberId) {
        return fineRepository.findUnpaidFinesByMemberId(memberId);
    }

    @Override
    public Fine updateFine(Fine fine) {
        if (!fineRepository.existsById(fine.getFineId())) {
            throw new RuntimeException("Fine not found with ID: " + fine.getFineId());
        }
        return fineRepository.save(fine);
    }
}
