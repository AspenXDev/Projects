package com.library.lms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;
import com.library.lms.repository.ReservationRepository;
import com.library.lms.service.ReservationService;

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
    public Reservation getReservationById(Integer reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByStatus(ReservationStatus status) {
        return reservationRepository.findByStatus(status);
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
    public Reservation updateReservation(Reservation reservation) {
        if (reservation.getReservationId() == null) {
            throw new IllegalArgumentException("Reservation ID must not be null for update");
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Integer reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
