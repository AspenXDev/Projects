package com.library.lms.service.impl;

import com.library.lms.model.Loan;
import com.library.lms.model.Member;
import com.library.lms.model.Book;
import com.library.lms.model.enums.LoanStatus;
import com.library.lms.repository.LoanRepository;
import com.library.lms.repository.BookRepository;
import com.library.lms.repository.MemberRepository;
import com.library.lms.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Loan borrowBook(Integer memberId, Integer bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book not available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusWeeks(2));
        loan.setStatus(LoanStatus.BORROWED);

        return loanRepository.save(loan);
    }

    @Override
    public Loan returnBook(Integer loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    @Override
    public Loan renewLoan(Integer loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (!LoanStatus.BORROWED.equals(loan.getStatus())) {
            throw new RuntimeException("Loan cannot be renewed");
        }

        loan.setDueDate(loan.getDueDate().plusWeeks(2));
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoansByMemberId(Integer memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return loanRepository.findByMember_MemberId(memberId);
    }

    @Override
    public List<Loan> getLoansByBookId(Integer bookId) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
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
    public Optional<Loan> getLoanById(Integer loanId) {
        return loanRepository.findById(loanId);
    }
}
