package com.library.lms.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.lms.exception.LoanNotFoundException;
import com.library.lms.model.Book;
import com.library.lms.model.Fine;
import com.library.lms.model.Loan;
import com.library.lms.model.Member;
import com.library.lms.repository.FineRepository;
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
    private final FineRepository fineRepository;

    public LoanServiceImpl(LoanRepository loanRepository,
                           MemberService memberService,
                           BookService bookService,
                           FineRepository fineRepository) {
        this.loanRepository = loanRepository;
        this.memberService = memberService;
        this.bookService = bookService;
        this.fineRepository = fineRepository;
    }

    @Override
    public Loan borrowBook(Integer memberId, Integer bookId) {
        Member member = memberService.getMemberById(memberId);
        Book book = bookService.getBookById(bookId);

        if (member.getMembershipValidUntil().isBefore(LocalDate.now())) {
            throw new RuntimeException("Membership expired. Cannot borrow.");
        }

        List<Loan> activeLoans = loanRepository.findByMember_MemberId(memberId)
                .stream()
                .filter(l -> l.getStatus() == Loan.LoanStatus.Active)
                .toList();

        if (activeLoans.size() >= 3) throw new RuntimeException("Borrowing limit reached (3 active loans).");

        boolean hasOverdue = activeLoans.stream()
                .anyMatch(l -> l.getDueDate().isBefore(LocalDate.now()) && l.getReturnDate() == null);
        if (hasOverdue) throw new RuntimeException("Cannot borrow with overdue loans.");

        BigDecimal unpaidFines = fineRepository.findByLoan_Member_MemberId(memberId)
                .stream()
                .filter(f -> !f.getPaid())
                .map(Fine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (unpaidFines.compareTo(BigDecimal.valueOf(10.0)) > 0)
            throw new RuntimeException("Outstanding fines exceed $10. Cannot borrow.");

        if (book.getAvailableCopies() <= 0) throw new RuntimeException("Book not available.");

        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));
        loan.setStatus(Loan.LoanStatus.Active);
        loan.setRenewCount(0);

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        return loanRepository.save(loan);
    }

    @Override
    public Loan returnBook(Integer loanId) {
        Loan loan = getLoanById(loanId);

        if (loan.getStatus() != Loan.LoanStatus.Active)
            throw new RuntimeException("Loan already returned.");

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(Loan.LoanStatus.Returned);

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            long daysOverdue = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
            BigDecimal fineAmount = BigDecimal.valueOf(Math.min(daysOverdue * 0.5, 20.0));

            Fine fine = new Fine();
            fine.setLoan(loan);
            fine.setAmount(fineAmount);
            fine.setPaid(false);

            fineRepository.save(fine);
        }

        return loanRepository.save(loan);
    }

    @Override
    public Loan renewLoan(Integer loanId) {
        Loan loan = getLoanById(loanId);

        if (loan.getStatus() != Loan.LoanStatus.Active)
            throw new RuntimeException("Only active loans can be renewed.");

        if (loan.getReturnDate() != null)
            throw new RuntimeException("Loan already returned.");

        if (loan.getDueDate().isBefore(LocalDate.now()))
            throw new RuntimeException("Cannot renew overdue loans.");

        if (loan.getRenewCount() >= 2)
            throw new RuntimeException("Maximum renewals reached (2).");

        loan.setDueDate(loan.getDueDate().plusDays(14));
        loan.setRenewCount(loan.getRenewCount() + 1);

        return loanRepository.save(loan);
    }

    @Override
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(Integer loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan with id " + loanId + " not found"));
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

        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Integer loanId) {
        Loan loan = getLoanById(loanId);
        loanRepository.delete(loan);
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
    public List<Loan> getLoansByStatus(Loan.LoanStatus status) {
        return loanRepository.findByStatus(status);
    }
}
