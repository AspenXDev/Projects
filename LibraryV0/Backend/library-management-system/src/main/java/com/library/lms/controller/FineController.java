package com.library.lms.controller;

import com.library.lms.model.Fine;
import com.library.lms.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fines")
@CrossOrigin(origins = "http://localhost:5173")
public class FineController {

    private final FineService fineService;

    @Autowired
    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    public ResponseEntity<List<Fine>> getAllFines() {
        return ResponseEntity.ok(fineService.getAllFines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fine> getFineById(@PathVariable Integer id) {
        Fine fine = fineService.getFineById(id)
                .orElseThrow(() -> new RuntimeException("Fine not found"));
        return ResponseEntity.ok(fine);
    }

    @GetMapping("/member/{memberId}/unpaid")
    public ResponseEntity<List<Fine>> getUnpaidFinesByMember(@PathVariable Integer memberId) {
        return ResponseEntity.ok(fineService.getUnpaidFinesByMemberId(memberId));
    }

    @PostMapping
    public ResponseEntity<Fine> createFine(@RequestBody Fine fine) {
        Fine created = fineService.createFine(fine);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fine> updateFine(@PathVariable Integer id, @RequestBody Fine fine) {
        Fine updated = fineService.updateFine(id, fine);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFine(@PathVariable Integer id) {
        fineService.deleteFine(id);
        return ResponseEntity.noContent().build();
    }
}
