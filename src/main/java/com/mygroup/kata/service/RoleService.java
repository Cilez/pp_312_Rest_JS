package com.mygroup.kata.service;


import com.mygroup.kata.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleById(Long id);
    Role getRoleByName(String name);
    void addRole(Role role);
    List<Role> getAllRoles();
}


