package com.library.lms.repository;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByMemberMemberId(Integer memberId);
    List<Reservation> findByBookBookId(Integer bookId);
    List<Reservation> findByStatus(ReservationStatus status);
}
