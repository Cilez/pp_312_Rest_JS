package com.mygroup.kata.init;

import com.mygroup.kata.model.Role;
import com.mygroup.kata.model.User;
import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final UserService userService;
    private final RoleService roleService;

    public ApplicationRunnerImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
            Optional<Role> admin = roleService.getRoleById(1L);
            Optional<Role> user = roleService.getRoleById(2L);
            Set<Role> adminRole = new HashSet<>();
            Set<Role> userRole = new HashSet<>();
            admin.ifPresent(adminRole::add);
            user.ifPresent((userRole::add));
            userService.addUser(new User("Misha", ("admin"), 23, adminRole, "Admin", "misha@email.com"));
            userService.addUser(new User("Dima", ("user"), 32, userRole, "User", "dima@email.com"));
            userService.addUser(new User("Kostya", ("dimab"), 54, userRole));
            userService.addUser(new User("vasyap", ("vasyap"), 12, userRole));
            userService.addUser(new User("kostyap", ("kostyag"), 10, userRole));
        }
    }
}