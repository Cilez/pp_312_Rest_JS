package com.mygroup.kata.repository;

import com.mygroup.kata.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByUsername(String username);
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
    UserDetails findUserByEmail(String email);
}
