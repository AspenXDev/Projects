package com.library.lms.mapper;

import com.library.lms.model.Book;
import com.library.lms.model.Loan;
import com.library.lms.model.Member;

public class LoanMapper {

    /**
     * Sets the Member and Book for an existing Loan entity.
     * Useful when creating or updating a Loan from partial data.
     */
    public static Loan toEntityWithMemberAndBook(Loan loan, Member member, Book book) {
        if (loan == null) return null;

        loan.setMember(member);
        loan.setBook(book);
        return loan;
    }
}
