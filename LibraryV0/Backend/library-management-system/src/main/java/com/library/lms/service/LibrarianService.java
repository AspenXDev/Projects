package com.library.lms.service;

import com.library.lms.model.Librarian;

import java.util.List;

public interface LibrarianService {

    Librarian createLibrarian(Librarian librarian);

    Librarian getLibrarianById(Integer librarianId);

    List<Librarian> getAllLibrarians();

    Librarian updateLibrarian(Integer librarianId, Librarian librarianDetails);

    void deleteLibrarian(Integer librarianId);
}
