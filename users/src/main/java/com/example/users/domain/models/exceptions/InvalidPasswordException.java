package com.example.users.domain.models.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("The password you entered is not valid.");
    }
}
