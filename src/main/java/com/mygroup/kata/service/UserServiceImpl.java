package com.mygroup.kata.service;


import com.mygroup.kata.model.User;
import com.mygroup.kata.Dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;

    }



    @Override
    public void addUser(User user) {
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
}

