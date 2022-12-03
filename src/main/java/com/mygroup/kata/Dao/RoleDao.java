package com.mygroup.kata.Dao;



import com.mygroup.kata.model.Role;

import java.util.List;


public interface RoleDao {

    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllRoles();

    void addRole(Role role);
}
