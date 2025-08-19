package com.library.lms.service.impl;

import com.library.lms.model.Member;
import com.library.lms.model.User;
import com.library.lms.repository.MemberRepository;
import com.library.lms.service.MemberService;
import com.library.lms.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final UserService userService;

    public MemberServiceImpl(MemberRepository memberRepository, UserService userService) {
        this.memberRepository = memberRepository;
        this.userService = userService;
    }

    @Override
    public Member createMember(Member member) {
        // Ensure the linked User exists
        Integer userId = member.getUser().getUserId();
        User user = userService.getUserById(userId);
        member.setUser(user);

        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(Integer memberId, Member memberDetails) {
        Member member = getMemberById(memberId);

        member.setFullName(memberDetails.getFullName());
        member.setRegistrationDate(memberDetails.getRegistrationDate());
        member.setMembershipValidUntil(memberDetails.getMembershipValidUntil());
        member.setMembershipStatus(memberDetails.getMembershipStatus());

        // Update linked User if provided
        if (memberDetails.getUser() != null) {
            Integer userId = memberDetails.getUser().getUserId();
            User user = userService.getUserById(userId);
            member.setUser(user);
        }

        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(Integer memberId) {
        Member member = getMemberById(memberId);
        memberRepository.delete(member);
    }
}
