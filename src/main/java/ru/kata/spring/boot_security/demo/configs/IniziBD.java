package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class IniziBD {
    private final UserService userService;


    public IniziBD(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initializeUsers() {
        User user = userService.findByEmail("admin@mail.ru");
        if (user == null) {
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(userRole);
            adminRoles.add(adminRole);
            User admin = new User(adminRoles, "ali", "tutu",20L, "admin@mail.ru", "admin");

            userService.save(admin);
        }
    }
}