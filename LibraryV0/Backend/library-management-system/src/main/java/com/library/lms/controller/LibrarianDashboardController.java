// path: Backend/src/main/java/com/library/lms/controller/LibrarianDashboardController.java
package com.library.lms.controller;

import com.library.lms.model.Member;
import com.library.lms.model.enums.LoanStatus;
import com.library.lms.repository.BookRepository;
import com.library.lms.repository.FineRepository;
import com.library.lms.repository.LoanRepository;
import com.library.lms.repository.MemberRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/dashboard/librarian")
@CrossOrigin(origins = "*")
public class LibrarianDashboardController {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;
    private final FineRepository fineRepository;

    public LibrarianDashboardController(
            BookRepository bookRepository,
            MemberRepository memberRepository,
            LoanRepository loanRepository,
            FineRepository fineRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.fineRepository = fineRepository;
    }

    /**
     * Return summary UI can use (optional now, future refactor can use this).
     * Example response:
     * {
     *   "books": { "total": 24, "borrowedTitles": 10, "activeLoans": 19 },
     *   "members": { "total": 100, "active": 80, "expired": 20 },
     *   "loans": { "active": 19, "overdue": 3 },
     *   "fines": { "unpaidTotal": 145.50 }
     * }
     */
    @GetMapping
    public ResponseEntity<?> getSummary(
            @RequestParam(value = "today", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate todayParam
    ) {
        LocalDate today = (todayParam != null) ? todayParam : LocalDate.now();

        long totalTitles = bookRepository.count();
        long borrowedTitles = bookRepository.countTitlesWithCopiesBorrowed();

        long membersTotal = memberRepository.count();
        long membersActive = memberRepository.countByMembershipStatus(Member.MembershipStatus.Active);
        long membersExpired = memberRepository.countByMembershipStatus(Member.MembershipStatus.Expired);

        long activeLoans = loanRepository.countByStatus(LoanStatus.Active);
        long overdueLoans = loanRepository.countByStatusAndDueDateBefore(LoanStatus.Active, today);

        Double unpaidFinesTotal = fineRepository.sumUnpaidAmounts();
        if (unpaidFinesTotal == null) unpaidFinesTotal = 0.0;

        Map<String, Object> payload = Map.of(
                "books", Map.of(
                        "total", totalTitles,
                        "borrowedTitles", borrowedTitles,   // titles with at least one copy out
                        "activeLoans", activeLoans          // number of copies currently on loan
                ),
                "members", Map.of(
                        "total", membersTotal,
                        "active", membersActive,
                        "expired", membersExpired
                ),
                "loans", Map.of(
                        "active", activeLoans,
                        "overdue", overdueLoans
                ),
                "fines", Map.of(
                        "unpaidTotal", unpaidFinesTotal
                )
        );

        return ResponseEntity.ok(payload);
    }
}
