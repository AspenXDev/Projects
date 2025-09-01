package com.library.lms.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.library.lms.mapper.LibrarianMapper;
import com.library.lms.model.Librarian;
import com.library.lms.model.User;
import com.library.lms.repository.LibrarianRepository;
import com.library.lms.repository.UserRepository;
import com.library.lms.service.LibrarianService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import com.library.lms.exception.MemberNotFoundException;
import com.library.lms.exception.BookNotFoundException;
import com.library.lms.exception.LoanNotFoundException;
import com.library.lms.exception.ReservationNotFoundException;

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
    public Librarian createLibrarian(Librarian librarian) {
        User user = librarian.getUser();
        if (user == null) throw new IllegalArgumentException("User must be provided for a Librarian");

        // Save the user first
        userRepository.save(user);

        Librarian mapped = LibrarianMapper.toEntityWithUser(librarian, user);
        return librarianRepository.save(mapped);
    }

    @Override
    public Librarian updateLibrarian(Integer id, Librarian librarian) {
        Librarian existing = librarianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found with ID: " + id));

        existing.setFullName(librarian.getFullName());

        // Preserve existing user
        existing.setUser(existing.getUser());

        return librarianRepository.save(existing);
    }

    @Override
    public void deleteLibrarian(Integer id) {
        if (!librarianRepository.existsById(id)) {
            throw new EntityNotFoundException("Librarian not found with ID: " + id);
        }
        librarianRepository.deleteById(id);
    }

    @Override
    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    @Override
    public Librarian getLibrarianById(Integer id) {
        return librarianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found with ID: " + id));
    }

    // --- Convenience methods ---
    @Override
    public List<Librarian> getLibrariansByFullName(String fullName) {
        return librarianRepository.findByFullName(fullName);
    }

    @Override
    public Librarian getLibrarianByUserId(Integer userId) {
        return librarianRepository.findByUserUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found with user ID: " + userId));
    }
}
