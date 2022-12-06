package com.mygroup.kata.init;

import com.mygroup.kata.model.Role;
import com.mygroup.kata.model.User;
import com.mygroup.kata.service.RoleService;
import com.mygroup.kata.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleService roleService;

    public ApplicationRunnerImpl(UserService userService, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
            Role admin = roleService.getRoleById(1L);
            Role user = roleService.getRoleById(2L);
            Set<Role> adminRole = new HashSet<>();
            Set<Role> userRole = new HashSet<>();
            adminRole.add(admin);
            userRole.add(user);
            String  email = "my@email.com";
            String lastName = "MyLastName";
            userService.addUser(new User( "Sergey", lastName, 10, passwordEncoder.encode("admin"), adminRole, email));
            userService.addUser(new User( "Dima", lastName, 11, passwordEncoder.encode("user"), userRole, email));
            userService.addUser(new User("Kostya", lastName, 12, passwordEncoder.encode("dimab"),  userRole, email));
            userService.addUser(new User("vasyap", lastName, 13, passwordEncoder.encode("vasyap"), userRole, email));
            userService.addUser(new User("vasyap", lastName, 14, passwordEncoder.encode("kostyag"), userRole, email));

        }
    }
}
