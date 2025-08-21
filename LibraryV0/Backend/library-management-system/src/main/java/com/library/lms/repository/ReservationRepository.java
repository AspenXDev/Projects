package com.library.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    // Find reservations by status
    List<Reservation> findByStatus(ReservationStatus status);

    // Find reservations by Member's primary key (traverse relationship)
    List<Reservation> findByMemberMemberId(Integer memberId);

    // Find reservations by Book's primary key (traverse relationship)
    List<Reservation> findByBookBookId(Integer bookId);
}
