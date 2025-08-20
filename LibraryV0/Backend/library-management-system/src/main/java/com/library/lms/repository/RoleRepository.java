package com.library.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.lms.model.enums.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);
}
