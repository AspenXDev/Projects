package com.library.lms.service;

import java.util.List;

import com.library.lms.model.Librarian;

public interface LibrarianService {
    Librarian createLibrarian(Librarian librarian);
    Librarian updateLibrarian(Integer id, Librarian librarian);
    void deleteLibrarian(Integer id);
    List<Librarian> getAllLibrarians();
    Librarian getLibrarianById(Integer id);

    // Other convenience methods
    List<Librarian> getLibrariansByFullName(String fullName);
    Librarian getLibrarianByUserId(Integer userId);
    
}
