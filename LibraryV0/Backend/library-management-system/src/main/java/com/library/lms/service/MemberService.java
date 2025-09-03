// path: Backend/src/main/java/com/library/lms/service/MemberService.java
package com.library.lms.service;

import com.library.lms.model.Member;
import com.library.lms.model.User;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member createMember(Member member, User user);
    Optional<Member> getMemberById(Integer memberId);
    Optional<Member> getMemberByUserId(Integer userId);
    Optional<Member> getMemberByUsername(String username);
    List<Member> getAllMembers();
    Member updateMember(Integer memberId, Member member);
    void deleteMember(Integer memberId);
}
