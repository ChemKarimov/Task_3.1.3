package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role role);
    List<Role> findAll();
    Optional<Role> findById(Long id);
    void deleteById(Long id);
    Optional<Role> findByName(String name);
}