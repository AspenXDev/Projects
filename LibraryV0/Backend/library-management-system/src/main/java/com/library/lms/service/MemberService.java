// PATH: src/main/java/com/library/lms/service/MemberService.java
package com.library.lms.service;

import com.library.lms.model.Member;
import com.library.lms.model.User;

import java.util.List;

public interface MemberService {
    Member createMember(Member member, User user);
    Member getMemberById(Integer memberId);
    List<Member> getAllMembers();
    Member updateMember(Integer memberId, Member member);
    void deleteMember(Integer memberId);
}
