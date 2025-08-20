package com.library.lms.mapper;

import com.library.lms.dto.MemberDTO;
import com.library.lms.dto.LibrarianDTO;
import com.library.lms.dto.UserDTO;
import com.library.lms.model.Member;
import com.library.lms.model.Librarian;
import com.library.lms.model.User;

public class MapperFactory {

    // Default: create User and assign safe role
    public static User toUser(UserDTO userDTO) {
        return UserMapper.toEntity(userDTO);
    }

    // Combine User + Member
    public static Member toMember(MemberDTO memberDTO) {
        User user = toUser(new UserDTO(
                null,
                memberDTO.fullName().toLowerCase().replaceAll("\\s+", ""),
                memberDTO.email(),
                null
        ));
        return MemberMapper.toEntityWithUser(memberDTO, user);
    }

    // Combine User + Librarian
    public static Librarian toLibrarian(LibrarianDTO librarianDTO) {
        User user = toUser(new UserDTO(
                null,
                librarianDTO.fullName().toLowerCase().replaceAll("\\s+", ""),
                librarianDTO.email(),
                "Librarian"
        ));
        return LibrarianMapper.toEntityWithUser(librarianDTO, user);
    }
}