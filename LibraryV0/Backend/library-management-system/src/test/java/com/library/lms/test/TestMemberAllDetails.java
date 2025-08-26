package com.library.lms.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.library.lms.model.Member;
import com.library.lms.service.MemberService;

@ActiveProfiles("test")
@SpringBootTest
class TestMemberAllDetails {

    @Autowired
    private MemberService memberService;

    @Test
    @Transactional(readOnly = true)
    void printMemberDetails() {
        String username = "alice_walker";
        Member member = memberService.getMemberFullDetails(username);

        System.out.println("{");
        System.out.println("  \"memberId\": " + member.getMemberId() + ",");
        System.out.println("  \"fullName\": \"" + member.getFullName() + "\",");
        System.out.println("  \"username\": \"" + member.getUser().getUsername() + "\",");
        System.out.println("  \"email\": \"" + member.getUser().getEmail() + "\",");
        System.out.println("  \"registrationDate\": \"" + member.getRegistrationDate() + "\",");
        System.out.println("  \"membershipValidUntil\": \"" + member.getMembershipValidUntil() + "\",");
        System.out.println("  \"status\": \"" + member.getMembershipStatus() + "\"");
        System.out.println("}");
    }
}
