package com.library.lms.repository;

import com.library.lms.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByFullName(String fullName);
}
