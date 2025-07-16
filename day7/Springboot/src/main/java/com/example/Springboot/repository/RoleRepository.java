package com.example.Springboot.repository;

import com.example.Springboot.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(String roleName);
}

