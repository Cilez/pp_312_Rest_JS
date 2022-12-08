package com.mygroup.kata.service;

import com.mygroup.kata.dao.RoleDao;
import com.mygroup.kata.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional(readOnly=true)
    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

}