package com.mygroup.kata.control;


import com.mygroup.kata.model.Role;
import com.mygroup.kata.model.User;
import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {


    private UserService userService;

    private RoleService roleService;

    private BCryptPasswordEncoder passwordEncoder;

    public AdminController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/allUsers")
    public String allUsers(@AuthenticationPrincipal User user, Model model){
        List<User> users = userService.getAllUsers();
//        users.add(user);
//        model.addAttribute("users", users);
        model.addAttribute("user", users);
        model.addAttribute("roles", roleService.getAllRoles());

        return "allUsers";
    }
    @GetMapping(value = "/create")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }
    @PostMapping(value = "/add")
    public String createUser(@ModelAttribute("user") User user,
                              @RequestParam(required=false) String roleAdmin) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleById(2L));
        if (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(roleService.getRoleById(1L));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);

        return "redirect:/admin/allUsers";
    }


    @GetMapping (value = "/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.findUserById(id).get();
        model.addAttribute("user", user);
        return "edit";
    }
    @PatchMapping (value = "/edit/{id}")
    public String userUpdate(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.editUser(user);
        return "redirect:/admin/allUsers";
    }



    @DeleteMapping ("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/allUsers";
    }
}
