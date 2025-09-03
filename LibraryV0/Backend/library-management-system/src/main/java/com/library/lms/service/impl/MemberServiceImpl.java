package com.library.lms.service.impl;

import com.library.lms.model.Member;
import com.library.lms.model.User;
import com.library.lms.repository.MemberRepository;
import com.library.lms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member createMember(Member member, User user) {
        member.setUser(user);
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> getMemberById(Integer memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Optional<Member> getMemberByUserId(Integer userId) {
        return memberRepository.findByUser_UserId(userId);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(Integer memberId, Member member) {
        Member existing = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        existing.setFullName(member.getFullName());
        existing.setMembershipValidUntil(member.getMembershipValidUntil());
        existing.setMembershipStatus(member.getMembershipStatus());
        return memberRepository.save(existing);
    }

    @Override
    public void deleteMember(Integer memberId) {
        memberRepository.deleteById(memberId);
    }
}
