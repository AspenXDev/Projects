package com.library.lms.service.impl;

import com.library.lms.model.Member;
import com.library.lms.repository.MemberRepository;
import com.library.lms.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(Integer memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (!member.isPresent()) {
            throw new RuntimeException("Member not found with ID: " + memberId);
        }
        return member.get();
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(Member member) {
        if (!memberRepository.existsById(member.getMemberId())) {
            throw new RuntimeException("Member not found with ID: " + member.getMemberId());
        }
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(Integer memberId) {
        memberRepository.deleteById(memberId);
    }
}
