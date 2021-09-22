package com.example.users.service.impl;

import com.example.users.domain.exceptions.UserNotExistException;
import com.example.users.domain.models.User;
import com.example.users.domain.models.UserId;
import com.example.users.domain.repository.UserRepository;
import com.example.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    /*
    Implementacija na UserService za manipulacija so korisnicite. Zavisni sme od userRepository koe e JpaRepository i so koe
    pravime promeni vo soodvetnata baza. Gi imame metodite na listanje na site korisnici, kako i na pobaruvanje na daden korisnik
    spoerd id.
     */

    private final UserRepository userRepository;

    @Override
    public User findById(UserId userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new UserNotExistException());
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
