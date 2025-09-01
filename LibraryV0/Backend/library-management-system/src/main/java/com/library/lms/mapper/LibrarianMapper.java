package com.library.lms.mapper;

import com.library.lms.model.Librarian;
import com.library.lms.model.User;

public class LibrarianMapper {

    public static Librarian toEntityWithUser(Librarian librarian, User user) {
        if (librarian == null) return null;
        if (user == null) throw new IllegalArgumentException("User cannot be null");

        librarian.setUser(user);
        return librarian;
    }

    public static Librarian createLibrarian(String fullName, User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        Librarian librarian = new Librarian();
        librarian.setFullName(fullName);
        librarian.setUser(user);
        return librarian;
    }
}
