package com.mygroup.kata.control;

import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("user", userService.getAllUsers());
        return "/boot/index";
    }
}
