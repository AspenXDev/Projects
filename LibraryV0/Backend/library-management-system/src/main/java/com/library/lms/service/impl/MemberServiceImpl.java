package com.library.lms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.lms.mapper.MemberMapper;
import com.library.lms.model.Member;
import com.library.lms.model.User;
import com.library.lms.repository.MemberRepository;
import com.library.lms.service.MemberService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(Member member) {
        // Ensure User is attached
        User user = member.getUser();
        if (user == null) throw new IllegalArgumentException("User must be provided for a Member");
        Member mappedMember = MemberMapper.toEntityWithUser(member, user);
        return memberRepository.save(mappedMember);
    }

    @Override
    public Member updateMember(Integer memberId, Member memberDetails) {
        Member existing = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        existing.setFullName(memberDetails.getFullName());
        existing.setRegistrationDate(memberDetails.getRegistrationDate());
        existing.setMembershipValidUntil(memberDetails.getMembershipValidUntil());
        existing.setMembershipStatus(memberDetails.getMembershipStatus());

        // Preserve User linkage
        if (memberDetails.getUser() != null) {
            existing.setUser(memberDetails.getUser());
        }

        return memberRepository.save(existing);
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public void deleteMember(Integer memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new EntityNotFoundException("Member not found with ID: " + memberId);
        }
        memberRepository.deleteById(memberId);
    }
}
