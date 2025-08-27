package com.library.lms.repository;

import com.library.lms.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByBookIdOrderByReservationDateAsc(Integer bookId);

    List<Reservation> findByMemberIdAndStatus(Integer memberId, String status);

    Optional<Reservation> findFirstByBookIdAndStatusOrderByReservationDateAsc(Integer bookId);

    boolean existsByBookIdAndMemberIdAndStatusIn(Integer bookId, Integer memberId, List<String> statuses);
}
