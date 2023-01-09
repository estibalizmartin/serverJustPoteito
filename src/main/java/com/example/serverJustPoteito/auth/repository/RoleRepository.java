package com.example.serverJustPoteito.auth.repository;

import com.example.serverJustPoteito.auth.persistence.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String role);
}