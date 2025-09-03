package com.library.lms.service.impl;

import com.library.lms.model.Member;
import com.library.lms.model.User;
import com.library.lms.repository.MemberRepository;
import com.library.lms.repository.UserRepository;
import com.library.lms.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public MemberServiceImpl(MemberRepository memberRepository, UserRepository userRepository) {
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

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
    public Optional<Member> getMemberByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> memberRepository.findByUser_UserId(user.getUserId()));
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
