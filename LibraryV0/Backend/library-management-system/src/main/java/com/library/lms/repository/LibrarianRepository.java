package com.library.lms.repository;

import java.util.List;
import java.util.Optional; 

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.lms.model.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    List<Librarian> findByFullName(String fullName);
    Optional<Librarian> findByUserUserId(Integer userId);  // find by user ID
}