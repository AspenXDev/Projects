package com.library.lms.service;

import com.library.lms.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Integer roleId);
}
