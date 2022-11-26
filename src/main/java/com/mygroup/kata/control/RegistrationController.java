package com.mygroup.kata.control;


import com.mygroup.kata.model.Role;
import com.mygroup.kata.model.User;
import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {



    private UserServiceImpl userService;
    private BCryptPasswordEncoder passwordEncoder;
    private RoleService roleService;

    public RegistrationController(UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("registration")
    public String addUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(2L));
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/";
    }


}

