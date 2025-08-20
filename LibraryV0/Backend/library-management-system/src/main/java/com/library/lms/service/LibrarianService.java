package com.library.lms.service;

import com.library.lms.model.Librarian;
import com.library.lms.dto.LibrarianDTO;
import java.util.List;

public interface LibrarianService {
    Librarian createLibrarian(LibrarianDTO librarianDTO);
    Librarian updateLibrarian(Integer id, LibrarianDTO librarianDTO);
    void deleteLibrarian(Integer id);
    List<Librarian> getAllLibrarians();
    Librarian getLibrarianById(Integer id);
}
