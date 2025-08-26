package com.library.lms.test;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.library.lms.model.Book;
import com.library.lms.model.Loan;
import com.library.lms.model.Member;
import com.library.lms.repository.BookRepository;
import com.library.lms.repository.MemberRepository;
import com.library.lms.util.LoanFineSetupHelper;

@ActiveProfiles("test")
@SpringBootTest
class TestLoanFineSetupHelper {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanFineSetupHelper loanFineSetupHelper;

    @Test
    @Transactional
    void testCreateLoanWithFines() {
        Member member = memberRepository.findByUsername("alice_walker")
                .orElseThrow(() -> new RuntimeException("Test member not found"));

        Book book = bookRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Test book not found"));

        Loan loan = loanFineSetupHelper.createLoanWithFines(
                member,
                book,
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(10),
                BigDecimal.valueOf(5.00),
                BigDecimal.valueOf(2.50)
        );

        System.out.println("Loan created: ID=" + loan.getLoanId());
        loan.getFines().forEach(f -> System.out.println("Fine: " + f.getFineId() + " amount=" + f.getAmount()));
    }
}
