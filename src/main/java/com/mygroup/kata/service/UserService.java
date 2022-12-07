package com.mygroup.kata.service;


import com.mygroup.kata.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    Optional<User> findUserById(Long id);

    List<User> getAllUsers();
}
