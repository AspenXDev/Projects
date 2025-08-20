package com.library.lms.mapper;

import com.library.lms.dto.LibrarianDTO;
import com.library.lms.model.Librarian;
import com.library.lms.model.User;

public class LibrarianMapper {

    public static LibrarianDTO toDTO(Librarian librarian) {
        if (librarian == null) return null;
        return new LibrarianDTO(
                librarian.getLibrarianId() != null ? librarian.getLibrarianId().longValue() : null,
                librarian.getFullName(),
                librarian.getUser() != null ? librarian.getUser().getEmail() : null,
                null
        );
    }

    public static Librarian toEntity(LibrarianDTO dto) {
        if (dto == null) return null;

        Librarian librarian = new Librarian();
        librarian.setLibrarianId(dto.id() != null ? dto.id().intValue() : null);
        librarian.setFullName(dto.fullName());

        return librarian;
    }

    // Chained mapper: Create Librarian with a User entity
    public static Librarian toEntityWithUser(LibrarianDTO dto, User user) {
        Librarian librarian = toEntity(dto);
        librarian.setUser(user);
        return librarian;
    }
}
