// PATH: src/main/java/com/library/lms/service/impl/MemberServiceImpl.java
package com.library.lms.service.impl;

import com.library.lms.model.Member;
import com.library.lms.model.User;
import com.library.lms.repository.MemberRepository;
import com.library.lms.service.MemberService;
import com.library.lms.mapper.MapperFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(Member member, User user) {
        Member newMember = MapperFactory.toMember(member, user);
        newMember.setRegistrationDate(LocalDate.now());
        newMember.setMembershipValidUntil(LocalDate.now().plusYears(1));
        return memberRepository.save(newMember);
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(Integer memberId, Member member) {
        Member existing = getMemberById(memberId);
        if (member.getFullName() != null) existing.setFullName(member.getFullName());
        return memberRepository.save(existing);
    }

    @Override
    public void deleteMember(Integer memberId) {
        memberRepository.deleteById(memberId);
    }
}
