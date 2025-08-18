package com.library.lms.repository;

import com.library.lms.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    // Add librarian-specific queries if needed
}
