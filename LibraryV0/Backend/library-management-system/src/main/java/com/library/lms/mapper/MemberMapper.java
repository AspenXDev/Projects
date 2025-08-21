package com.library.lms.mapper;

import com.library.lms.model.Member;
import com.library.lms.model.User;

public class MemberMapper {

    public static Member toEntityWithUser(Member member, User user) {
        if (member == null) return null;
        if (user == null) throw new IllegalArgumentException("User cannot be null");

        member.setUser(user);
        return member;
    }

    // Optional: convenience method to create a Member from scratch with a User
    public static Member createMember(String fullName, User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        Member member = new Member();
        member.setFullName(fullName);
        member.setUser(user);
        return member;
    }
}
