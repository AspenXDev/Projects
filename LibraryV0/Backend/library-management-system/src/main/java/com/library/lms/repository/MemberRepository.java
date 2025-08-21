package com.library.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.lms.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    // Find members by full name
    List<Member> findByFullName(String fullName);
}
