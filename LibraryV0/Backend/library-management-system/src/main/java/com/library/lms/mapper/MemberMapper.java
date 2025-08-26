// MemberMapper.java
package com.library.lms.mapper;

import com.library.lms.dto.MemberDetailsDTO;
import com.library.lms.dto.MemberPureDTO;
import com.library.lms.model.Member;
import com.library.lms.model.User;

import java.time.LocalDateTime;

/**
 * Mapper for converting between {@link Member} entity and {@link MemberPureDTO} / {@link MemberDetailsDTO} records.
 */
public class MemberMapper {

    /**
     * Converts a {@link Member} entity to a {@link MemberPureDTO} record.
     * This DTO directly mirrors the 'members' table.
     *
     * @param member The Member entity to convert.
     * @return The corresponding MemberPureDTO record, or null if the entity is null.
     */
    public static MemberPureDTO toDTO(Member member) {
        if (member == null) {
            return null;
        }
        return new MemberPureDTO(
                member.getMemberId(),
                member.getUser() != null ? member.getUser().getUserId() : null,
                member.getFullName(),
                member.getRegistrationDate(),
                member.getMembershipValidUntil(),
                member.getMembershipStatus() != null ? member.getMembershipStatus().name() : null, // Convert enum to String
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link Member} entity to a {@link MemberDetailsDTO} record.
     * This DTO combines Member, User, and Role information.
     *
     * @param member The Member entity to convert.
     * @return The corresponding MemberDetailsDTO record, or null if the entity is null.
     */
    public static MemberDetailsDTO toDetailsDTO(Member member) {
        if (member == null) {
            return null;
        }
        User user = member.getUser();
        return new MemberDetailsDTO(
                member.getMemberId(),
                // Member-specific fields
                member.getFullName(),
                member.getRegistrationDate(),
                member.getMembershipValidUntil(),
                member.getMembershipStatus() != null ? member.getMembershipStatus().name() : null,
                // User-specific fields
                user != null ? user.getUserId() : null,
                user != null ? user.getUsername() : null,
                user != null ? user.getEmail() : null,
                user != null ? user.getIsActive() : null,
                // Role-specific field
                user != null && user.getRole() != null ? user.getRole().getRoleName() : null,
                // Timestamps
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link MemberPureDTO} record to a new {@link Member} entity.
     * Note: This method does not set the associated User entity. Use {@link #toEntity(MemberPureDTO, User)}
     * or set the User in the service layer after this conversion.
     *
     * @param dto The MemberPureDTO record to convert.
     * @return A new Member entity, or null if the DTO is null.
     */
    public static Member toEntity(MemberPureDTO dto) {
        if (dto == null) {
            return null;
        }
        Member member = new Member();
        member.setMemberId(dto.memberId()); // memberId might be null for new entities
        member.setFullName(dto.fullName());
        member.setRegistrationDate(dto.registrationDate());
        member.setMembershipValidUntil(dto.membershipValidUntil());
        if (dto.membershipStatus() != null) {
            member.setMembershipStatus(Member.MembershipStatus.valueOf(dto.membershipStatus()));
        }
        // Timestamps createdAt and updatedAt are typically set by the database
        return member;
    }

    /**
     * Converts a {@link MemberPureDTO} record to a new {@link Member} entity and associates it with an existing {@link User}.
     *
     * @param dto The MemberPureDTO record to convert.
     * @param user The existing User entity to associate with the Member.
     * @return A new Member entity with the associated User, or null if the DTO is null.
     * @throws IllegalArgumentException if the User is null.
     */
    public static Member toEntity(MemberPureDTO dto, User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null when creating a Member entity.");
        Member member = toEntity(dto); // Use the base conversion
        if (member != null) {
            member.setUser(user);
        }
        return member;
    }

    /**
     * Updates an existing {@link Member} entity with data from a {@link MemberPureDTO} record.
     *
     * @param member The existing Member entity to update.
     * @param dto The MemberPureDTO record containing the updated data.
     * @return The updated Member entity.
     */
    public static Member updateEntityFromDTO(Member member, MemberPureDTO dto) {
        if (member == null || dto == null) {
            return member;
        }
        member.setFullName(dto.fullName());
        member.setRegistrationDate(dto.registrationDate());
        member.setMembershipValidUntil(dto.membershipValidUntil());
        if (dto.membershipStatus() != null) {
            member.setMembershipStatus(Member.MembershipStatus.valueOf(dto.membershipStatus()));
        }
        // userId and timestamps are typically not updated directly through a DTO update.
        // If userId needs to change, it's usually a more complex operation involving user management.
        return member;
    }
}

/////////////////////////////////////////////////////////////////////
