package com.example.users.config;

import com.example.users.domain.models.User;
import com.example.users.service.UserService;
import com.example.users.service.forms.UserForm;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class DataInitializer {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }
/*
    @PostConstruct
    public void initAdmins() {
        Optional<User> user1 = userService.findByUserName("filip");
        if (user1.isEmpty()) {
            UserForm userForm = new UserForm("filip", "Filip", "Stavrov", "filip", "filip");
            this.userService.register(userForm);
        }
    }

 */
}