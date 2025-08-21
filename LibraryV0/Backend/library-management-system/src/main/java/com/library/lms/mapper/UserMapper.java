package com.library.lms.mapper;

import com.library.lms.dto.UserDTO;
import com.library.lms.model.Role;
import com.library.lms.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getUserId() != null ? user.getUserId().longValue() : null,
                user.getUsername(),
                user.getEmail(),
                user.getRole() != null ? user.getRole().getRoleName() : null
        );
    }

    // SAFE: Default all new users to "Member" role
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUserId(dto.id() != null ? dto.id().intValue() : null);
        user.setUsername(dto.username());
        user.setEmail(dto.email());

        Role defaultRole = new Role();
        defaultRole.setRoleName("Member");
        user.setRole(defaultRole);

        return user;
    }
}
