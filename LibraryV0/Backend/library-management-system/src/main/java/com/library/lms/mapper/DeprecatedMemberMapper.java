//package com.library.lms.mapper;
//
//import com.library.lms.model.Member;
//import com.library.lms.model.User;
//
//import java.time.LocalDate;
//
//public class DeprecatedMemberMapper {
//
//    public static Member createMember(String fullName, User user) {
//        if (user == null) throw new IllegalArgumentException("User cannot be null");
//
//        Member member = new Member();
//        member.setUser(user);
//        member.setFullName(fullName);
//        member.setRegistrationDate(LocalDate.now());
//        member.setMembershipValidUntil(LocalDate.now().plusYears(1));
//        member.setMembershipStatus(Member.MembershipStatus.Active);
//        return member;
//    }
//}
