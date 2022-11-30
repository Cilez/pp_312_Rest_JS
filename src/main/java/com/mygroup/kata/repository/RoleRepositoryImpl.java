package com.mygroup.kata.repository;


import com.mygroup.kata.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RoleRepositoryImpl implements RoleRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r ", Role.class)
                .getResultList();
    }
    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
    @Override
    public Role getRoleByName(String name) {
        return entityManager.find(Role.class, name);
    }

}


