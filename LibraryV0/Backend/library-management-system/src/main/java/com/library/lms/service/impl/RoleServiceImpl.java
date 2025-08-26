package com.library.lms.service.impl;

import com.library.lms.model.Role;
import com.library.lms.repository.RoleRepository;
import com.library.lms.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Role updateRole(Integer roleId, Role roleDetails) {
        Role existing = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + roleId));
        existing.setRoleName(roleDetails.getRoleName());
        return roleRepository.save(existing);
    }

    @Override
    public void deleteRole(Integer roleId) {
        Role existing = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + roleId));
        roleRepository.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + roleId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with name " + roleName));
    }
}
