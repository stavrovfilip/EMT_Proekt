package com.example.users.service;

import com.example.users.domain.models.User;
import com.example.users.domain.models.UserId;

import java.util.List;

public interface UserService {

    User findById(UserId userId);
    List<User> findAll();
}
