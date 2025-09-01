package com.library.lms.service.impl;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;
import com.library.lms.repository.ReservationRepository;
import com.library.lms.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID " + id));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByMemberId(Integer memberId) {
        return reservationRepository.findByMemberMemberId(memberId);
    }

    @Override
    public List<Reservation> getReservationsByBookId(Integer bookId) {
        return reservationRepository.findByBookBookId(bookId);
    }

    @Override
    public List<Reservation> getReservationsByStatus(ReservationStatus status) {
        return reservationRepository.findByStatus(status);
    }

    @Override
    public Reservation updateReservation(Integer id, Reservation reservationDetails) {
        Reservation existing = getReservationById(id);
        existing.setBook(reservationDetails.getBook());
        existing.setMember(reservationDetails.getMember());
        existing.setStatus(reservationDetails.getStatus());
        existing.setHoldUntil(reservationDetails.getHoldUntil());
        return reservationRepository.save(existing);
    }

    @Override
    public void deleteReservation(Integer id) {
        Reservation existing = getReservationById(id);
        reservationRepository.delete(existing);
    }
}
