package com.library.lms.controller;

import com.library.lms.model.Reservation;
import com.library.lms.model.enums.ReservationStatus;
import com.library.lms.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        try {
            Reservation created = reservationService.createReservation(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to create reservation");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(reservationService.getReservationById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving reservation");
        }
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getReservationsByStatus(@PathVariable String status) {
        try {
            ReservationStatus reservationStatus = ReservationStatus.fromDb(status);
            return ResponseEntity.ok(reservationService.getReservationsByStatus(reservationStatus));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid reservation status: " + status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving reservations");
        }
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getReservationsByMember(@PathVariable Integer memberId) {
        try {
            return ResponseEntity.ok(reservationService.getReservationsByMemberId(memberId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reservations found for member ID: " + memberId);
        }
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getReservationsByBook(@PathVariable Integer bookId) {
        try {
            return ResponseEntity.ok(reservationService.getReservationsByBookId(bookId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reservations found for book ID: " + bookId);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
        try {
            return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update reservation");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to delete reservation");
        }
    }
}