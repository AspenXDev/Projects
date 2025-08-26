package com.library.lms.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.library.lms.model.Member;
import com.library.lms.repository.MemberRepository;

@ActiveProfiles("test")
@SpringBootTest
class TestMemberID {

    @Autowired
    private MemberRepository memberRepo;

    @Test
    void testFindMemberByUsername() {
        Member m = memberRepo.findByUsername("victor_chan").orElse(null);

        if (m != null) {
            System.out.println("Member ID: " + m.getMemberId());
            System.out.println("Full Name: " + m.getFullName());
        } else {
            System.out.println("Member not found");
        }
    }
}
