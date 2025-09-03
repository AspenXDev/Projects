package com.library.lms.controller;

import com.library.lms.model.Loan;
import com.library.lms.model.Member;
import com.library.lms.model.User;
import com.library.lms.model.enums.LoanStatus;
import com.library.lms.service.LoanService;
import com.library.lms.service.MemberService;
import com.library.lms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    private final LoanService loanService;
    private final UserService userService;
    private final MemberService memberService;

    public LoanController(LoanService loanService, UserService userService, MemberService memberService) {
        this.loanService = loanService;
        this.userService = userService;
        this.memberService = memberService;
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

    @GetMapping("/my")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<List<Loan>> getMyLoans(Authentication auth) {
        // Unwrap Optional<User>
    	User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        // Unwrap Optional<Member>
        Member member = memberService.getMemberByUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<Loan> loans = loanService.getLoansByMemberId(member.getMemberId());
        return ResponseEntity.ok(loans);
    }
}
