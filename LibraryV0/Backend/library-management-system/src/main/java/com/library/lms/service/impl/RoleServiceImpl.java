package com.library.lms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.lms.model.Role;
import com.library.lms.repository.RoleRepository;
import com.library.lms.service.RoleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Integer roleId, Role roleDetails) {
        Role role = getRoleById(roleId);
        role.setRoleName(roleDetails.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer roleId) {
        Role role = getRoleById(roleId);
        roleRepository.delete(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
    }
}
