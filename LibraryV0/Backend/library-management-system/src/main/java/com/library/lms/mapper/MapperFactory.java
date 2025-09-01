// PATH: src/main/java/com/library/lms/mapper/MapperFactory.java
package com.library.lms.mapper;

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

    public static Member toMember(Member member, User user) {
        Member m = new Member();
        m.setFullName(member.getFullName());
        m.setUser(user);
        return m;
    }
}
