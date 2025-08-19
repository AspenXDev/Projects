package com.library.lms.service.impl;

import com.library.lms.model.Librarian;
import com.library.lms.model.User;
import com.library.lms.repository.LibrarianRepository;
import com.library.lms.service.LibrarianService;
import com.library.lms.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final UserService userService; // For handling User relationship

    public LibrarianServiceImpl(LibrarianRepository librarianRepository, UserService userService) {
        this.librarianRepository = librarianRepository;
        this.userService = userService;
    }

    @Override
    public Librarian createLibrarian(Librarian librarian) {
        // Ensure the linked User exists
        Integer userId = librarian.getUser().getUserId();
        User user = userService.getUserById(userId);
        librarian.setUser(user);

        return librarianRepository.save(librarian);
    }

    @Override
    public Librarian getLibrarianById(Integer librarianId) {
        return librarianRepository.findById(librarianId)
                .orElseThrow(() -> new RuntimeException("Librarian not found with ID: " + librarianId));
    }

    @Override
    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    @Override
    public Librarian updateLibrarian(Integer librarianId, Librarian librarianDetails) {
        Librarian librarian = getLibrarianById(librarianId);

        librarian.setFullName(librarianDetails.getFullName());

        // Update linked User if provided
        if (librarianDetails.getUser() != null) {
            Integer userId = librarianDetails.getUser().getUserId();
            User user = userService.getUserById(userId);
            librarian.setUser(user);
        }

        return librarianRepository.save(librarian);
    }

    @Override
    public void deleteLibrarian(Integer librarianId) {
        Librarian librarian = getLibrarianById(librarianId);
        librarianRepository.delete(librarian);
    }
}