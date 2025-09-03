package com.library.lms.controller;

import com.library.lms.model.Librarian;
import com.library.lms.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
@CrossOrigin(origins = "http://localhost:5173")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @GetMapping("/{id}")
    public ResponseEntity<Librarian> getLibrarian(@PathVariable Integer id) {
        Librarian librarian = librarianService.getLibrarianById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));
        return ResponseEntity.ok(librarian);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Librarian> getByUserId(@PathVariable Integer userId) {
        Librarian librarian = librarianService.getLibrarianByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));
        return ResponseEntity.ok(librarian);
    }

    @GetMapping
    public ResponseEntity<List<Librarian>> getAllLibrarians() {
        return ResponseEntity.ok(librarianService.getAllLibrarians());
    }

    @PostMapping
    public ResponseEntity<Librarian> createLibrarian(@RequestBody Librarian librarian) {
        return ResponseEntity.ok(librarianService.createLibrarian(librarian));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Librarian> updateLibrarian(@PathVariable Integer id, @RequestBody Librarian librarian) {
        return ResponseEntity.ok(librarianService.updateLibrarian(id, librarian));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrarian(@PathVariable Integer id) {
        librarianService.deleteLibrarian(id);
        return ResponseEntity.noContent().build();
    }
}
