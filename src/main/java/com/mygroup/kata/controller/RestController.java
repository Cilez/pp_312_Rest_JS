package com.mygroup.kata.controller;

import com.mygroup.kata.model.User;
import com.mygroup.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/api")
public class RestController {
    private UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    // users, roles, allUsers

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        for (User u : list) {
            u.setRoles(new HashSet<>());
        }
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
