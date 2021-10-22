package com.example.users.domain.models.dto;

import com.example.users.domain.enumerations.Role;
import com.example.users.domain.models.User;
import lombok.Data;

@Data
public class UserDetailsDto {

    private String username;
    private Role role;

    public static UserDetailsDto of(User user){
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}
