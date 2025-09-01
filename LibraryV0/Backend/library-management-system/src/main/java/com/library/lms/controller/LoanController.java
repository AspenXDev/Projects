package com.library.lms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.lms.model.Loan;
import com.library.lms.service.LoanService;
import com.library.lms.model.enums.LoanStatus;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // ======================
    // Domain endpoints
    // ======================
    @PostMapping("/borrow/{memberId}/{bookId}")
    public Loan borrowBook(@PathVariable Integer memberId, @PathVariable Integer bookId) {
        return loanService.borrowBook(memberId, bookId);
    }

    @PutMapping("/return/{loanId}")
    public Loan returnBook(@PathVariable Integer loanId) {
        return loanService.returnBook(loanId);
    }

    @PutMapping("/renew/{loanId}")
    public Loan renewLoan(@PathVariable Integer loanId) {
        return loanService.renewLoan(loanId);
    }

    // ======================
    // Convenience
    // ======================
    @GetMapping("/member/{memberId}")
    public List<Loan> getLoansByMember(@PathVariable Integer memberId) {
        return loanService.getLoansByMemberId(memberId);
    }

    @GetMapping("/book/{bookId}")
    public List<Loan> getLoansByBook(@PathVariable Integer bookId) {
        return loanService.getLoansByBookId(bookId);
    }

    @GetMapping("/status/{status}")
    public List<Loan> getLoansByStatus(@PathVariable LoanStatus status) {
        return loanService.getLoansByStatus(status);

    }
}