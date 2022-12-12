package com.mygroup.kata.dao;

import com.mygroup.kata.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    UserDetails getUserByUsername(String username);

    UserDetails findUserByEmail(String email);
}