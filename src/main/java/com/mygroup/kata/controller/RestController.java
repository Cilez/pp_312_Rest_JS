package com.mygroup.kata.controller;

import com.mygroup.kata.model.Role;
import com.mygroup.kata.model.User;
import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/api")
public class RestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // users, roles, allUsers

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
//        for (User u : list) {
//            u.setRoles(new HashSet<>());
//        }
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getALlRoles() {
        List<Role> list = roleService.getAllRoles();
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
        return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.editUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/oneUser")
    public ResponseEntity<User> showUser(Authentication auth) {
        return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
    }
}
