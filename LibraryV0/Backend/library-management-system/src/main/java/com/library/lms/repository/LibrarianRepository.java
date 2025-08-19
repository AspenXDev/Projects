package com.library.lms.repository;

import com.library.lms.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    List<Librarian> findByFullName(String fullName);
}