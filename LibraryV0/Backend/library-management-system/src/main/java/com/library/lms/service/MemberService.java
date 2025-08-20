package com.library.lms.service;

import com.library.lms.model.Member;
import com.library.lms.dto.MemberDTO;
import java.util.List;

public interface MemberService {
    Member createMember(MemberDTO memberDTO);
    Member updateMember(Integer id, MemberDTO memberDTO);
    void deleteMember(Integer id);
    List<Member> getAllMembers();
    Member getMemberById(Integer id);
}
