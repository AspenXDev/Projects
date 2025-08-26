package com.library.lms.service.impl;

import com.library.lms.model.Librarian;
import com.library.lms.repository.LibrarianRepository;
import com.library.lms.service.LibrarianService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;

    public LibrarianServiceImpl(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public Librarian createLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    @Override
    public Librarian updateLibrarian(Integer librarianId, Librarian librarian) {
        Librarian existing = librarianRepository.findById(librarianId)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found: " + librarianId));
        existing.setFullName(librarian.getFullName());
        return librarianRepository.save(existing);
    }

    @Override
    public void deleteLibrarian(Integer librarianId) {
        librarianRepository.delete(getLibrarianById(librarianId));
    }

    @Override
    @Transactional(readOnly = true)
    public Librarian getLibrarianById(Integer librarianId) {
        return librarianRepository.findById(librarianId)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found: " + librarianId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Librarian> getLibrariansByFullName(String fullName) {
        return librarianRepository.findByFullNameContainingIgnoreCase(fullName);
    }

    @Override
    @Transactional(readOnly = true)
    public Librarian getLibrarianByUserId(Integer userId) {
        return librarianRepository.findByUserUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found with userId " + userId));
    }
}
