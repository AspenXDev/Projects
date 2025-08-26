package com.library.lms.service.impl;

import com.library.lms.model.Loan;
import com.library.lms.repository.LoanRepository;
import com.library.lms.service.LoanService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan updateLoan(Integer loanId, Loan loan) {
        Loan existing = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found: " + loanId));
        existing.setBook(loan.getBook());
        existing.setMember(loan.getMember());
        existing.setDueDate(loan.getDueDate());
        existing.setReturnDate(loan.getReturnDate());
        existing.setRenewCount(loan.getRenewCount());
        existing.setStatus(loan.getStatus());
        return loanRepository.save(existing);
    }

    @Override
    public void deleteLoan(Integer loanId) {
        loanRepository.delete(getLoanById(loanId));
    }

    @Override
    @Transactional(readOnly = true)
    public Loan getLoanById(Integer loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found: " + loanId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> getLoansByStatus(Loan.LoanStatus status) {
        return loanRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> getLoansByMemberId(Integer memberId) {
        return loanRepository.findByMemberMemberId(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> getLoansByBookId(Integer bookId) {
        return loanRepository.findByBookBookId(bookId);
    }
}
