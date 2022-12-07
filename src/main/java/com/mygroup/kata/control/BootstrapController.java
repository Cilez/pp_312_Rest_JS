package com.mygroup.kata.control;

import com.mygroup.kata.model.Role;
import com.mygroup.kata.model.User;
import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/boot")
public class BootstrapController {
    private UserService userService;

    private RoleService roleService;

    private BCryptPasswordEncoder passwordEncoder;

    public BootstrapController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/index")
    public String indexPage(Model model) {
        model.addAttribute("user", userService.getAllUsers().get(0));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/boot/index";
    }

    @GetMapping("/edit/{id}")
    public String userForm(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("userFromForm", user);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public void userSubmit(@ModelAttribute User user, @RequestParam(value = "role") String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(role == "ROLE_USER") {
            user.setRoles((List<Role>) roleService.getRoleById(2L));
        } if(role == "ROLE_ADMIN") {
            user.setRoles((List<Role>) roleService.getRoleById(1L));
        }
        userService.editUser(user);
    }


    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleById(2L));
        if(roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(roleService.getRoleById(1L));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "boot/index";

    }

    @GetMapping(value = "/create")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "boot/index";
    }
}
