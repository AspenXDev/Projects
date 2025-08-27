package com.library.lms.service;

import com.library.lms.model.Member;
import java.util.List;

public interface MemberService {
    Member createMember(Member member);
    Member getMemberById(Integer memberId);
    List<Member> getAllMembers();
    Member updateMember(Member member);
    void deleteMember(Integer memberId);
}
