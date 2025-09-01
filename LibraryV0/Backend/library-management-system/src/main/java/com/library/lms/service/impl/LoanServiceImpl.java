package com.library.lms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.lms.model.Loan;
import com.library.lms.model.enums.LoanStatus;
import com.library.lms.repository.LoanRepository;
import com.library.lms.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan borrowBook(Integer memberId, Integer bookId) {
        // implement borrowing logic here
        throw new UnsupportedOperationException("Implement borrow logic");
    }

    @Override
    public Loan returnBook(Integer loanId) {
        // implement return logic here
        throw new UnsupportedOperationException("Implement return logic");
    }

    @Override
    public Loan renewLoan(Integer loanId) {
        // implement renewal logic here
        throw new UnsupportedOperationException("Implement renew logic");
    }

    @Override
    public List<Loan> getLoansByMemberId(Integer memberId) {
        return loanRepository.findByMember_MemberId(memberId);
    }

    @Override
    public List<Loan> getLoansByBookId(Integer bookId) {
        return loanRepository.findByBook_BookId(bookId);
    }

    @Override
    public List<Loan> getLoansByStatus(LoanStatus status) {
        return loanRepository.findByStatus(status);
    }
}
