package com.library.lms.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.library.lms.model.Book;
import com.library.lms.model.Fine;
import com.library.lms.model.Loan;
import com.library.lms.model.Member;
import com.library.lms.repository.FineRepository;
import com.library.lms.repository.LoanRepository;

@Component
public class LoanFineSetupHelper {

    private final LoanRepository loanRepository;
    private final FineRepository fineRepository;

    public LoanFineSetupHelper(LoanRepository loanRepository, FineRepository fineRepository) {
        this.loanRepository = loanRepository;
        this.fineRepository = fineRepository;
    }

    @Transactional
    public Loan createLoanWithFines(Member member, Book book, LocalDate loanDate, LocalDate dueDate, BigDecimal... finesAmounts) {
        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(loanDate);
        loan.setDueDate(dueDate);
        loan.setStatus(Loan.LoanStatus.Active);
        loan.setFines(new HashSet<>());

        // Save loan first to get its ID
        loan = loanRepository.save(loan);

        for (BigDecimal amount : finesAmounts) {
            Fine fine = new Fine();
            fine.setLoan(loan);
            fine.setAmount(amount);
            fine.setPaid(false);

            fine = fineRepository.save(fine);
            loan.getFines().add(fine);
        }

        return loanRepository.save(loan);
    }
}
