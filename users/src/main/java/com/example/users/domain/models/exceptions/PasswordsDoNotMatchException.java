package com.example.users.domain.models.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("The passwords do not match.");
    }
}
