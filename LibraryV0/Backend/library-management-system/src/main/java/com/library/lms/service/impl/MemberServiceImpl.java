package com.library.lms.service.impl;

import com.library.lms.model.Member;
import com.library.lms.dto.MemberDTO;
import com.library.lms.repository.MemberRepository;
import com.library.lms.repository.UserRepository;
import com.library.lms.service.MemberService;
import com.library.lms.mapper.MapperFactory;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public MemberServiceImpl(MemberRepository memberRepository, UserRepository userRepository) {
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Member createMember(MemberDTO memberDTO) {
        Member member = MapperFactory.toMember(memberDTO);
        userRepository.save(member.getUser());
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Integer id, MemberDTO memberDTO) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Member updated = MapperFactory.toMember(memberDTO);
        updated.setMemberId(existing.getMemberId());
        updated.setUser(existing.getUser()); // preserve user entity
        return memberRepository.save(updated);
    }

    @Override
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }
}
