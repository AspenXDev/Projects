package com.library.lms.service.impl;

import com.library.lms.model.Librarian;
import com.library.lms.repository.LibrarianRepository;
import com.library.lms.service.LibrarianService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;

    @Autowired
    public LibrarianServiceImpl(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public Librarian createLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    @Override
    public Librarian getLibrarianById(Integer librarianId) {
        Optional<Librarian> librarian = librarianRepository.findById(librarianId);
        if (!librarian.isPresent()) {
            throw new RuntimeException("Librarian not found with ID: " + librarianId);
        }
        return librarian.get();
    }

    @Override
    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    @Override
    public Librarian updateLibrarian(Librarian librarian) {
        if (!librarianRepository.existsById(librarian.getLibrarianId())) {
            throw new RuntimeException("Librarian not found with ID: " + librarian.getLibrarianId());
        }
        return librarianRepository.save(librarian);
    }

    @Override
    public void deleteLibrarian(Integer librarianId) {
        librarianRepository.deleteById(librarianId);
    }
}
