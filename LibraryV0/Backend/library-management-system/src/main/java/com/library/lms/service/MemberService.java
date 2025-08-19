package com.library.lms.service;

import com.library.lms.model.Member;

import java.util.List;

public interface MemberService {

    Member createMember(Member member);

    Member getMemberById(Integer memberId);

    List<Member> getAllMembers();

    Member updateMember(Integer memberId, Member memberDetails);

    void deleteMember(Integer memberId);
}
