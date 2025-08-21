package com.library.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;
import com.library.lms.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation created = reservationService.createReservation(reservation);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@PathVariable ReservationStatus status) {
        List<Reservation> reservations = reservationService.getReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Reservation>> getReservationsByMember(@PathVariable Integer memberId) {
        List<Reservation> reservations = reservationService.getReservationsByMemberId(memberId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Reservation>> getReservationsByBook(@PathVariable Integer bookId) {
        List<Reservation> reservations = reservationService.getReservationsByBookId(bookId);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
        reservation.setReservationId(id);
        Reservation updated = reservationService.updateReservation(reservation);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
