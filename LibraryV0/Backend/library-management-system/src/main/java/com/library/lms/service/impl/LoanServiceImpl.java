package com.library.lms.service.impl;

import com.library.lms.model.Loan;
import com.library.lms.model.enums.LoanStatus;
import com.library.lms.repository.LoanRepository;
import com.library.lms.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan borrowBook(Integer memberId, Integer bookId) {
        throw new UnsupportedOperationException("Borrow logic will be implemented in Batch #3");
    }

    @Override
    public Loan returnBook(Integer loanId) {
        throw new UnsupportedOperationException("Return logic will be implemented in Batch #3");
    }

    @Override
    public Loan renewLoan(Integer loanId) {
        throw new UnsupportedOperationException("Renew logic will be implemented in Batch #3");
    }

    @Override
    public Optional<Loan> getLoanById(Integer loanId) {
        return loanRepository.findById(loanId);
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

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> findActiveLoansByUserId(Integer userId) {
        return loanRepository.findByMemberUserUserIdAndStatus(userId, LoanStatus.Active);
    }

    @Override
    public List<Loan> findOverdueLoansByUserId(Integer userId) {
        LocalDate today = LocalDate.now();
        return loanRepository.findByMemberUserUserIdAndStatus(userId, LoanStatus.Active)
                .stream()
                .filter(loan -> loan.getDueDate() != null && loan.getDueDate().isBefore(today))
                .peek(loan -> loan.setStatus(LoanStatus.Overdue))
                .collect(Collectors.toList());
    }
}
