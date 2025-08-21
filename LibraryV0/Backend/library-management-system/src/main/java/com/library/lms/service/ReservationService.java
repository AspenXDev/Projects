package com.library.lms.service;

import java.util.List;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation getReservationById(Integer reservationId);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByStatus(ReservationStatus status);
    List<Reservation> getReservationsByMemberId(Integer memberId);
    List<Reservation> getReservationsByBookId(Integer bookId);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(Integer reservationId);
}
