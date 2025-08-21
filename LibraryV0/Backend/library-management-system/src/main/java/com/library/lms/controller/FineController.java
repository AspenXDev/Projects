package com.library.lms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.lms.model.Fine;
import com.library.lms.service.FineService;

@RestController
@RequestMapping("/fines")
public class FineController {

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    // ======================
    // CRUD Operations
    // ======================

    @GetMapping
    public List<Fine> getAllFines() {
        return fineService.getAllFines();
    }

    @GetMapping("/{id}")
    public Fine getFineById(@PathVariable Integer id) {
        return fineService.getFineById(id);
    }

    @PostMapping
    public Fine createFine(@RequestBody Fine fine) {
        return fineService.createFine(fine);
    }

    @PutMapping("/{id}")
    public Fine updateFine(@PathVariable Integer id, @RequestBody Fine fineDetails) {
        return fineService.updateFine(id, fineDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteFine(@PathVariable Integer id) {
        fineService.deleteFine(id);
    }

    // ======================
    // Convenience Endpoints
    // ======================

    @GetMapping("/loan/{loanId}")
    public List<Fine> getFinesByLoanId(@PathVariable Integer loanId) {
        return fineService.getFinesByLoanId(loanId);
    }

    @GetMapping("/member/{memberId}")
    public List<Fine> getFinesByMemberId(@PathVariable Integer memberId) {
        return fineService.getFinesByMemberId(memberId);
    }

    @GetMapping("/paid/{paid}")
    public List<Fine> getFinesByPaidStatus(@PathVariable Boolean paid) {
        return fineService.getFinesByPaidStatus(paid);
    }
}
