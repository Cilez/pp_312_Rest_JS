package com.mygroup.kata.control;


import com.mygroup.kata.model.Role;
import com.mygroup.kata.model.User;
import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {



    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;
    private RoleService roleService;

    public RegistrationController(UserService userService, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(2L));
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/";
    }

//    @GetMapping("/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/boot/index";
    } // делает пыр-пыр, при заходе на сервер


}

