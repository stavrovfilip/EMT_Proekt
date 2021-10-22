package com.example.users.service.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserForm {

    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String password;

    @NotNull
    private String repeatPassword;

    public UserForm(String username, String name, String surname, String password, String repeatPassword) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

}
