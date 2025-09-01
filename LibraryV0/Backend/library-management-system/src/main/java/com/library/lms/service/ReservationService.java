package com.library.lms.service;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    Reservation getReservationById(Integer id);

    List<Reservation> getAllReservations();

    List<Reservation> getReservationsByMemberId(Integer memberId);

    List<Reservation> getReservationsByBookId(Integer bookId);

    List<Reservation> getReservationsByStatus(ReservationStatus status);

    Reservation updateReservation(Integer id, Reservation reservationDetails);

    void deleteReservation(Integer id);
}
