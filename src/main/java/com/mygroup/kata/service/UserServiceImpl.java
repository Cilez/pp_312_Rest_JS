package com.mygroup.kata.service;

import com.mygroup.kata.dao.UserDao;
import com.mygroup.kata.model.User;
import com.mygroup.kata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder passwordEncoder, UserRepository u) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = u;
    }


//    @Override
//    @Transactional
//    public void addUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userDao.addUser(user);
//    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }



    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findUserByEmail(email);
    }

    @Override
    public User findUserByName(String name) {
        return (User) userDao.getUserByUsername(name);
    }
}