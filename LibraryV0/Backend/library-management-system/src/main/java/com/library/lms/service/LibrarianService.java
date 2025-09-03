package com.library.lms.service;

import com.library.lms.model.Librarian;

import java.util.List;
import java.util.Optional;

public interface LibrarianService {

    Librarian createLibrarian(Librarian librarian);

    Optional<Librarian> getLibrarianById(Integer librarianId);

    Optional<Librarian> getLibrarianByUserId(Integer userId);

    List<Librarian> getAllLibrarians();

    Librarian updateLibrarian(Integer librarianId, Librarian librarian);

    void deleteLibrarian(Integer librarianId);
}
