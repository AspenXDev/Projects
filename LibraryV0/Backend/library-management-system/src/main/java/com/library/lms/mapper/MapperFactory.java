package com.library.lms.mapper;

import com.library.lms.model.Librarian;
import com.library.lms.model.Member;
import com.library.lms.model.Role;
import com.library.lms.model.User;

public class MapperFactory {

    public static User createUser(String username, String email, String passwordHash, String roleName) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordHash);
        user.setIsActive(true);
        user.setRole(new Role(roleName));
        return user;
    }

    public static Member toMember(Member member) {
        User user = createUser(
                member.getFullName().toLowerCase().replaceAll("\\s+", ""),
                member.getUser() != null ? member.getUser().getEmail() : "",
                member.getUser() != null ? member.getUser().getPasswordHash() : "",
                "members"
        );
        return MemberMapper.toEntityWithUser(member, user);
    }

    public static Librarian toLibrarian(Librarian librarian) {
        User user = createUser(
                librarian.getFullName().toLowerCase().replaceAll("\\s+", ""),
                librarian.getUser() != null ? librarian.getUser().getEmail() : "",
                librarian.getUser() != null ? librarian.getUser().getPasswordHash() : "",
                "librarians"
        );
        return LibrarianMapper.toEntityWithUser(librarian, user);
    }
}
