package com.library.lms.service;

import com.library.lms.model.Reservation;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Integer memberId, Integer bookId);
    Reservation cancelReservation(Integer reservationId);
    List<Reservation> getReservationsByMemberId(Integer memberId);
    List<Reservation> getReservationsByBookId(Integer bookId);
    void processReservationQueue(Integer bookId);
}
