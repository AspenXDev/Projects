package com.library.lms.service.impl;

import com.library.lms.model.Librarian;
import com.library.lms.dto.LibrarianDTO;
import com.library.lms.repository.LibrarianRepository;
import com.library.lms.repository.UserRepository;
import com.library.lms.service.LibrarianService;
import com.library.lms.mapper.MapperFactory;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final UserRepository userRepository;

    public LibrarianServiceImpl(LibrarianRepository librarianRepository, UserRepository userRepository) {
        this.librarianRepository = librarianRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Librarian createLibrarian(LibrarianDTO librarianDTO) {
        Librarian librarian = MapperFactory.toLibrarian(librarianDTO);
        userRepository.save(librarian.getUser());
        return librarianRepository.save(librarian);
    }

    @Override
    public Librarian updateLibrarian(Integer id, LibrarianDTO librarianDTO) {
        Librarian existing = librarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));
        Librarian updated = MapperFactory.toLibrarian(librarianDTO);
        updated.setLibrarianId(existing.getLibrarianId());
        updated.setUser(existing.getUser()); // preserve user entity
        return librarianRepository.save(updated);
    }

    @Override
    public void deleteLibrarian(Integer id) {
        librarianRepository.deleteById(id);
    }

    @Override
    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    @Override
    public Librarian getLibrarianById(Integer id) {
        return librarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));
    }
}