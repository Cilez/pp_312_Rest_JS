package com.mygroup.kata.dao;

import com.mygroup.kata.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
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
