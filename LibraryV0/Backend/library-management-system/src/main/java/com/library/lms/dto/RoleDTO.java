// RoleDTO.java
package com.library.lms.dto;

/**
 * A Data Transfer Object for representing Role information.
 * This record is used for data exchange between the service layer and the controllers/client.
 */
public record RoleDTO(
    Integer roleId,
    String roleName
) {}
