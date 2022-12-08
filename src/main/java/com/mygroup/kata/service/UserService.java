package com.mygroup.kata.service;

import com.mygroup.kata.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    UserDetails loadUserByUsername(String username);

    @Transactional(readOnly = true)
    User findUserByName(String name);
}