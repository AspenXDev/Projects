// RoleMapper.java
package com.library.lms.mapper;

import com.library.lms.dto.*;
import com.library.lms.model.*;
/**
 * Mapper for converting between {@link Role} entity and {@link RoleDTO} record.
 */
public class RoleMapper {

    /**
     * Converts a {@link Role} entity to a {@link RoleDTO} record.
     *
     * @param role The Role entity to convert.
     * @return The corresponding RoleDTO record, or null if the entity is null.
     */
    public static RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleDTO(
            role.getRoleId(),
            role.getRoleName()
        );
    }

    /**
     * Converts a {@link RoleDTO} record to a {@link Role} entity.
     * This method is typically used when creating or updating a Role.
     * Note: For updating an existing Role, you might need to fetch the entity first.
     *
     * @param dto The RoleDTO record to convert.
     * @return The corresponding Role entity, or null if the DTO is null.
     */
    public static Role toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setRoleId(dto.roleId());
        role.setRoleName(dto.roleName());
        return role;
    }
}