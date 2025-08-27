package com.library.lms.repository;

import com.library.lms.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByUserId(Integer userId);

    boolean existsByUserIdAndMembershipStatusAndMembershipValidUntilAfter(
            Integer userId, String status, LocalDate date
    );
}
