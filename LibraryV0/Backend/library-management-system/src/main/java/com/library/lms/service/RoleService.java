package com.library.lms.service;

import java.util.List;

import com.library.lms.model.Role;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleById(Integer roleId);
    List<Role> getAllRoles();
    Role updateRole(Integer roleId, Role roleDetails);
    void deleteRole(Integer roleId);

    // Convenience
    Role getRoleByName(String roleName);
}
