package com.mygroup.kata.control;

import com.mygroup.kata.model.User;
import com.mygroup.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId) {
        User user = userService.findUserById(userId).get();
        user.getRoles().get(0).setUsers(new ArrayList<>());
        return user;
    }

    @PostMapping
    public void create(@RequestBody User user) {
        userService.addUser(user);
    }

}
