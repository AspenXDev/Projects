package com.library.lms.service.impl;

import com.library.lms.model.Reservation;
import com.library.lms.repository.ReservationRepository;
import com.library.lms.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
    public Reservation updateReservation(Integer reservationId, Reservation reservation) {
        Reservation existing = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found: " + reservationId));
        existing.setReservationDate(reservation.getReservationDate());
        existing.setExpiryDate(reservation.getExpiryDate());
        existing.setBook(reservation.getBook());
        existing.setMember(reservation.getMember());
        return reservationRepository.save(existing);
    }

    @Override
    public void deleteReservation(Integer reservationId) {
        reservationRepository.delete(getReservationById(reservationId));
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation getReservationById(Integer reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found: " + reservationId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByBookId(Integer bookId) {
        return reservationRepository.findByBookBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByMemberId(Integer memberId) {
        return reservationRepository.findByMemberMemberId(memberId);
    }
}
