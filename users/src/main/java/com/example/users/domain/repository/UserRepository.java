package com.example.users.domain.repository;

import com.example.users.domain.models.User;
import com.example.users.domain.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId> {
}
