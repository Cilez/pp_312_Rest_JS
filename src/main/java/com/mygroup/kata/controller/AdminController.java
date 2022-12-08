package com.mygroup.kata.controller;


import com.mygroup.kata.model.User;
import com.mygroup.kata.service.RoleServiceImpl;
import com.mygroup.kata.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private UserServiceImpl userService;

    private RoleServiceImpl roleService;



    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping("/allUsers")
    public String allUsers(Model model, Principal principal) {
        model.addAttribute("userAdmin", userService.findUserByName(principal.getName()));
        List<User> user = userService.getAllUsers();
        model.addAttribute("newUser", new User());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());



        return "allUsers";
    }

    @PostMapping(value = "/add")
    public String createUser (@ModelAttribute("user") User user, @RequestParam(value = "role") String role) {
        if(role.equals("ROLE_USER")) {
            user.setRoles(Set.of(roleService.getRoleById(2L)));
        } else if(role.equals("ROLE_ADMIN")) {
            user.setRoles(Set.of(roleService.getRoleById(1L)));
        }

        userService.addUser(user);

        return "redirect:/admin/allUsers";
    }


    @PatchMapping(value = "/edit/{id}")
    public String userUpdate(@ModelAttribute("user") User user, @RequestParam(value = "role") String role) {

        if(role.equals("ROLE_USER")) {
            user.setRoles(Set.of(roleService.getRoleById(2L)));
        } else if(role.equals("ROLE_ADMIN")) {
            user.setRoles(Set.of(roleService.getRoleById(1L)));
        }
        userService.editUser(user);
        return "redirect:/admin/allUsers";
    }
     /*@PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "role") String role) {
        user.setRoles(Set.of(roleService.getRoleByName(role)));
        userService.editUser(user);
        return "redirect:/admin/allUsers";
    }*/



    @DeleteMapping ("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/allUsers";
    }
}