package com.library.lms.service.impl;

import com.library.lms.model.Book;
import com.library.lms.model.Member;
import com.library.lms.model.Reservation;
import com.library.lms.repository.ReservationRepository;
import com.library.lms.service.ReservationService;
import com.library.lms.service.MemberService;
import com.library.lms.service.BookService;
import com.library.lms.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberService memberService;
    private final BookService bookService;
    private final LoanService loanService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, MemberService memberService,
                                  BookService bookService, LoanService loanService) {
        this.reservationRepository = reservationRepository;
        this.memberService = memberService;
        this.bookService = bookService;
        this.loanService = loanService;
    }

    @Override
    public Reservation createReservation(Integer memberId, Integer bookId) {
        Member member = memberService.getMemberById(memberId);
        Book book = bookService.getBookById(bookId);

        // Cannot reserve a book already borrowed by member
        boolean hasActiveLoan = loanService.getActiveLoansByMemberId(memberId)
                .stream().anyMatch(l -> l.getBook().getBookId().equals(bookId));
        if (hasActiveLoan) {
            throw new RuntimeException("Member already has this book on loan.");
        }

        // Cannot reserve book already reserved by member
        boolean alreadyReserved = reservationRepository.existsByMemberIdAndBookIdAndStatusIn(memberId, bookId, List.of("Waiting","On Hold"));
        if (alreadyReserved) {
            throw new RuntimeException("Member has already reserved this book.");
        }

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setBook(book);
        reservation.setStatus("Waiting");
        reservation.setReservationDate(LocalDateTime.now());

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation cancelReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus("Cancelled");
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByMemberId(Integer memberId) {
        return reservationRepository.findByMemberId(memberId);
    }

    @Override
    public List<Reservation> getReservationsByBookId(Integer bookId) {
        return reservationRepository.findByBookId(bookId);
    }

    @Override
    public void processReservationQueue(Integer bookId) {
        Book book = bookService.getBookById(bookId);
        if (book.getAvailableCopies() <= 0) return;

        List<Reservation> waitingList = reservationRepository.findTopByBookIdAndStatusOrderByReservationDateAsc(bookId, "Waiting");
        if (!waitingList.isEmpty()) {
            Reservation first = waitingList.get(0);
            first.setStatus("On Hold");
            first.setHoldUntil(LocalDateTime.now().plusDays(3));
            reservationRepository.save(first);
        }
    }
}
