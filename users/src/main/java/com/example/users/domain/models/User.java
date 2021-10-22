package com.example.users.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.users.domain.enumerations.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "Ai_users")
@Getter
public class User extends AbstractEntity<UserId> implements UserDetails {

    /*
    Klasa za korisnik koj ima username, password, name, surname.
     */

    private String username;

    private String name;

    private String surname;

    private String password;

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    protected User() {
        super(UserId.randomId(UserId.class));
    }


    public User(String username,
                String name,
                String surname,
                String password,
                Role role) {
        super(UserId.randomId(UserId.class));
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }
}