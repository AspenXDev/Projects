package com.library.lms.repository;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStatus(ReservationStatus status);
    List<Reservation> findByMemberId(Long memberId);
    List<Reservation> findByBookId(Long bookId);
}
