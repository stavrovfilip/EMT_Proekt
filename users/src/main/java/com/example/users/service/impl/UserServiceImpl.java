package com.example.users.service.impl;

import com.example.users.domain.enumerations.Role;
import com.example.users.domain.exceptions.UserNotExistException;
import com.example.users.domain.models.User;
import com.example.users.domain.models.UserId;
import com.example.users.domain.models.exceptions.InvalidPasswordException;
import com.example.users.domain.models.exceptions.InvalidUsernameException;
import com.example.users.domain.models.exceptions.PasswordsDoNotMatchException;
import com.example.users.domain.models.exceptions.UsernameAlreadyExistsException;
import com.example.users.domain.repository.UserRepository;
import com.example.users.service.UserService;
import com.example.users.service.forms.UserForm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    /*
    Implementacija na UserService za manipulacija so korisnicite. Zavisni sme od userRepository koe e JpaRepository i so koe
    pravime promeni vo soodvetnata baza. Gi imame metodite za registracija na korisnik, i prebaruvanje na daden
    korsnik po id kako i po username.
     */

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> register(UserForm userForm) {
        if (userForm.getUsername() == null || userForm.getUsername().isEmpty())
            throw new InvalidUsernameException();

        if (userForm.getPassword() == null || userForm.getPassword().isEmpty())
            throw new InvalidPasswordException();

        if (this.userRepository.findByUsername(userForm.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(userForm.getUsername());
        }

        if (!userForm.getPassword().equals(userForm.getRepeatPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        User user =  userRepository.save(new User(
                userForm.getUsername(),
                userForm.getName(),
                userForm.getSurname(),
                passwordEncoder.encode(userForm.getPassword()),
                Role.ROLE_USER));

        return Optional.of(user);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByUserId(UserId userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }
}
