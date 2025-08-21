package com.library.lms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.lms.model.Librarian;
import com.library.lms.service.LibrarianService;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    // ======================
    // CRUD Endpoints
    // ======================
    @PostMapping
    public Librarian createLibrarian(@RequestBody Librarian librarian) {
        return librarianService.createLibrarian(librarian);
    }

    @GetMapping("/{id}")
    public Librarian getLibrarianById(@PathVariable Integer id) {
        return librarianService.getLibrarianById(id);
    }

    @GetMapping
    public List<Librarian> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    @PutMapping("/{id}")
    public Librarian updateLibrarian(@PathVariable Integer id, @RequestBody Librarian librarian) {
        return librarianService.updateLibrarian(id, librarian);
    }

    @DeleteMapping("/{id}")
    public void deleteLibrarian(@PathVariable Integer id) {
        librarianService.deleteLibrarian(id);
    }
}
