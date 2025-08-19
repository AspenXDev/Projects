package com.library.lms.service;

import com.library.lms.model.Role;
import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleById(Integer roleId);
    List<Role> getAllRoles();
    Role updateRole(Integer roleId, Role roleDetails);
    void deleteRole(Integer roleId);

    // Convenience
    Role getRoleByName(String roleName);
}
