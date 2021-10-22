package com.example.users.service;

import com.example.users.domain.models.User;
import com.example.users.domain.models.UserId;
import com.example.users.service.forms.UserForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> register(UserForm userForm);
    Optional<User> findByUserName(String username);
    Optional<User> findByUserId(UserId userId);
}
