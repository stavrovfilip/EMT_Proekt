package com.example.users.domain.repository;

import com.example.users.domain.models.User;
import com.example.users.domain.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId> {
    Optional<User> findByUsername(String username);
}
