package com.library.lms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.lms.model.Loan;
import com.library.lms.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // ======================
    // CRUD
    // ======================
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Integer id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Integer id, @RequestBody Loan loanDetails) {
        return loanService.updateLoan(id, loanDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Integer id) {
        loanService.deleteLoan(id);
    }

    // ======================
    // Convenience endpoints
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
    public List<Loan> getLoansByStatus(@PathVariable Loan.LoanStatus status) {
        return loanService.getLoansByStatus(status);
    }
}
