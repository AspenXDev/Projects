package com.library.lms.service.impl;

import com.library.lms.model.Librarian;
import com.library.lms.repository.LibrarianRepository;
import com.library.lms.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    @Override
    public Librarian createLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    @Override
    public Optional<Librarian> getLibrarianById(Integer librarianId) {
        return librarianRepository.findById(librarianId);
    }

    @Override
    public Optional<Librarian> getLibrarianByUserId(Integer userId) {
        return librarianRepository.findByUser_UserId(userId);
    }

    @Override
    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    @Override
    public Librarian updateLibrarian(Integer librarianId, Librarian librarian) {
        Librarian existing = librarianRepository.findById(librarianId)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));
        existing.setFullName(librarian.getFullName());
        return librarianRepository.save(existing);
    }

    @Override
    public void deleteLibrarian(Integer librarianId) {
        librarianRepository.deleteById(librarianId);
    }
}
