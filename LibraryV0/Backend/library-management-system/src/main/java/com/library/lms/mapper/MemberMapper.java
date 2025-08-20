package com.library.lms.mapper;

import com.library.lms.dto.MemberDTO;
import com.library.lms.model.Member;
import com.library.lms.model.User;

public class MemberMapper {

    // Basic DTO mapping
    public static MemberDTO toDTO(Member member) {
        if (member == null) return null;
        return new MemberDTO(
                member.getMemberId() != null ? member.getMemberId().longValue() : null,
                member.getFullName(),
                member.getUser() != null ? member.getUser().getEmail() : null,
                null,
                member.getRegistrationDate(),
                member.getMembershipValidUntil(),
                member.getMembershipStatus() != null ? member.getMembershipStatus().name() : null
        );
    }

    // Entity mapping without user
    public static Member toEntity(MemberDTO dto) {
        if (dto == null) return null;

        Member member = new Member();
        member.setMemberId(dto.id() != null ? dto.id().intValue() : null);
        member.setFullName(dto.fullName());
        member.setRegistrationDate(dto.membershipStart());
        member.setMembershipValidUntil(dto.membershipEnd());
        member.setMembershipStatus(dto.membershipStatus() != null ?
                Member.MembershipStatus.valueOf(dto.membershipStatus()) : Member.MembershipStatus.Active);

        return member;
    }

    // Chained mapper: Create Member with a User entity
    public static Member toEntityWithUser(MemberDTO dto, User user) {
        Member member = toEntity(dto);
        member.setUser(user);
        return member;
    }
}