package com.library.lms.repository;

import com.library.lms.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUser_UserId(Integer userId);
}