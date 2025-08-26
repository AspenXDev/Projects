package com.library.lms.service;

import java.util.List;
import com.library.lms.model.Member;

public interface MemberService {

    Member createMember(Member member);

    Member updateMember(Integer memberId, Member member);

    Member getMemberById(Integer memberId);

    Member getMemberFullDetails(String username); // fetch with loans, fines, reservations

    List<Member> getAllMembers();

    void deleteMember(Integer memberId);
}
