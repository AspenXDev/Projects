package com.library.lms.service.impl;

import com.library.lms.model.Book;
import com.library.lms.model.Loan;
import com.library.lms.model.Member;
import com.library.lms.model.Fine;
import com.library.lms.repository.LoanRepository;
import com.library.lms.service.LoanService;
import com.library.lms.service.BookService;
import com.library.lms.service.MemberService;
import com.library.lms.service.FineService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final MemberService memberService;
    private final FineService fineService;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, BookService bookService, MemberService memberService, FineService fineService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.memberService = memberService;
        this.fineService = fineService;
    }

    @Override
    public Loan createLoan(Integer memberId, Integer bookId) {
        Member member = memberService.getMemberById(memberId);
        Book book = bookService.getBookById(bookId);
        LocalDate today = LocalDate.now();

        // Eligibility checks
        if (!"Active".equals(member.getMembershipStatus()) || member.getMembershipValidUntil().isBefore(today)) {
            throw new RuntimeException("Membership inactive or expired.");
        }

        List<Loan> overdueLoans = loanRepository.findByMemberIdAndStatusAndDueDateBefore(memberId, "Active", today);
        if (!overdueLoans.isEmpty()) {
            throw new RuntimeException("Member has overdue loans.");
        }

        BigDecimal totalUnpaidFines = fineService.getUnpaidFinesByMemberId(memberId)
                .stream().map(Fine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalUnpaidFines.compareTo(new BigDecimal("10")) > 0) {
            throw new RuntimeException("Member has unpaid fines exceeding $10.");
        }

        long activeLoansCount = loanRepository.countByMemberIdAndStatus(memberId, "Active");
        if (activeLoansCount >= 3) {
            throw new RuntimeException("Member has reached maximum active loans.");
        }

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book not available for loan.");
        }

        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(today);
        loan.setDueDate(today.plusDays(14));
        loan.setRenewCount(0);
        loan.setStatus("Active");

        bookService.updateAvailableCopies(book.getBookId(), book.getAvailableCopies() - 1);

        return loanRepository.save(loan);
    }

    @Override
    public Loan returnLoan(Integer loanId) {
        Loan loan = getLoanById(loanId);
        if (!"Active".equals(loan.getStatus())) {
            throw new RuntimeException("Loan is already returned.");
        }

        LocalDate today = LocalDate.now();
        loan.setReturnDate(today);
        loan.setStatus("Returned");

        // Calculate fines
        if (loan.getDueDate().isBefore(today)) {
            long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(loan.getDueDate(), today);
            double fineAmount = Math.min(overdueDays * 0.5, 20.0);
            Fine fine = new Fine();
            fine.setLoan(loan);
            fine.setAmount(BigDecimal.valueOf(fineAmount));
            fine.setPaid(false);
            fineService.createFine(fine);
        }

        Book book = loan.getBook();
        bookService.updateAvailableCopies(book.getBookId(), book.getAvailableCopies() + 1);

        return loanRepository.save(loan);
    }

    @Override
    public Loan renewLoan(Integer loanId) {
        Loan loan = getLoanById(loanId);
        if (!"Active".equals(loan.getStatus())) {
            throw new RuntimeException("Cannot renew a returned loan.");
        }

        if (loan.getRenewCount() >= 2) {
            throw new RuntimeException("Maximum renewals reached.");
        }

        loan.setDueDate(loan.getDueDate().plusDays(14));
        loan.setRenewCount(loan.getRenewCount() + 1);

        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(Integer loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));
    }

    @Override
    public List<Loan> getLoansByMemberId(Integer memberId) {
        return loanRepository.findByMemberId(memberId);
    }

    @Override
    public List<Loan> getActiveLoansByMemberId(Integer memberId) {
        return loanRepository.findByMemberIdAndStatus(memberId, "Active");
    }
}
