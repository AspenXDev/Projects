// path: Backend/src/main/java/com/library/lms/repository/MemberRepository.java
package com.library.lms.repository;

import com.library.lms.model.Member;
import com.library.lms.model.Member.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUser_UserId(Integer userId);
    long countByMembershipStatus(MembershipStatus status);
}
