package com.library.lms.repository;

import com.library.lms.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Member-specific queries in future version
}
