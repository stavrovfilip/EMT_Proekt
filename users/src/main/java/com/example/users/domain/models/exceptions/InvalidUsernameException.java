package com.example.users.domain.models.exceptions;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException() {
        super("The username you entered is not valid.");
    }
}
