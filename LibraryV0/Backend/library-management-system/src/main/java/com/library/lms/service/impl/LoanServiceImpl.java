package com.library.lms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.lms.mapper.LoanMapper;
import com.library.lms.model.Book;
import com.library.lms.model.Loan;
import com.library.lms.model.Member;
import com.library.lms.repository.LoanRepository;
import com.library.lms.service.BookService;
import com.library.lms.service.LoanService;
import com.library.lms.service.MemberService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final MemberService memberService;
    private final BookService bookService;

    public LoanServiceImpl(LoanRepository loanRepository,
                           MemberService memberService,
                           BookService bookService) {
        this.loanRepository = loanRepository;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @Override
    public Loan createLoan(Loan loan) {
        // Ensure linked Member and Book exist
        Member member = memberService.getMemberById(loan.getMember().getMemberId());
        Book book = bookService.getBookById(loan.getBook().getBookId());

        // Use LoanMapper to set Member and Book
        LoanMapper.toEntityWithMemberAndBook(loan, member, book);

        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(Integer loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan updateLoan(Integer loanId, Loan loanDetails) {
        Loan loan = getLoanById(loanId);

        loan.setLoanDate(loanDetails.getLoanDate());
        loan.setDueDate(loanDetails.getDueDate());
        loan.setReturnDate(loanDetails.getReturnDate());
        loan.setRenewCount(loanDetails.getRenewCount());
        loan.setStatus(loanDetails.getStatus());

        // Update linked Member and Book if provided, using the mapper
        Member member = loanDetails.getMember() != null
                ? memberService.getMemberById(loanDetails.getMember().getMemberId())
                : loan.getMember();

        Book book = loanDetails.getBook() != null
                ? bookService.getBookById(loanDetails.getBook().getBookId())
                : loan.getBook();

        LoanMapper.toEntityWithMemberAndBook(loan, member, book);

        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Integer loanId) {
        Loan loan = getLoanById(loanId);
        loanRepository.delete(loan);
    }

    // --- Convenience methods ---
    @Override
    public List<Loan> getLoansByMemberId(Integer memberId) {
        return loanRepository.findByMember_MemberId(memberId);
    }

    @Override
    public List<Loan> getLoansByBookId(Integer bookId) {
        return loanRepository.findByBook_BookId(bookId);
    }

    @Override
    public List<Loan> getLoansByStatus(Loan.LoanStatus status) {
        return loanRepository.findByStatus(status);
    }
}
