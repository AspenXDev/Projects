package com.library.lms.service.impl;

import com.library.lms.model.Role;
import com.library.lms.repository.RoleRepository;
import com.library.lms.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (!role.isPresent()) {
            throw new RuntimeException("Role not found with ID: " + roleId);
        }
        return role.get();
    }
}
