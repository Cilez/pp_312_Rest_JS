package com.mygroup.kata.service;

import com.mygroup.kata.dao.UserDao;
import com.mygroup.kata.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);

    }
    @Transactional(readOnly=true)
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
    @Transactional(readOnly=true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getUserByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByName(String name) {
        return (User) userDao.getUserByUsername(name);
    }
}