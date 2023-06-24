package com.example.lab1.exception.Security;

public class UserProfileNotFoundException extends RuntimeException {

    public UserProfileNotFoundException(Long id) {
        super("Could not find user profile " + id);
    }

}