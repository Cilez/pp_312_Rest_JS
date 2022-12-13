package com.mygroup.kata.repository;

import com.mygroup.kata.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role getRoleByName(String name);
}
