package com.mygroup.kata.control;

import com.mygroup.kata.model.Role;
import com.mygroup.kata.model.User;
import com.mygroup.kata.model.UserCreationDTO;
import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        User user = userService.findUserById(id).get();
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

    @GetMapping("/create")
    public String addUser(Model model) {
        UserCreationDTO udto = new UserCreationDTO();
        for (int i = 1; i < 3; i++) {
            udto.addUser(new User());
        }
        model.addAttribute("user", udto);
        return "boot/createUserForm";
    }

    // new
    @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:index";
    }

    // new add
    @PostMapping("/add")
    public String addNewUser(@ModelAttribute("user") User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleById(2L));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:index";

    }

    // new
    @RequestMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        User user = userService.findUserById(id).get();
        user.setRoles(new ArrayList<>());
        return user;
    }
}
