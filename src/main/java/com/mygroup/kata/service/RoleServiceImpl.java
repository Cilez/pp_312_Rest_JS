package com.mygroup.kata.service;


import com.mygroup.kata.model.Role;
import com.mygroup.kata.repository.RoleRepository;
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
    public void addRole(Role role) {
        roleRepository.addRole(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Transactional(readOnly=true)
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }
    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

}