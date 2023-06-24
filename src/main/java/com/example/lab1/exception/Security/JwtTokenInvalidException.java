package com.example.lab1.exception.Security;

public class JwtTokenInvalidException extends RuntimeException {

    public JwtTokenInvalidException(String token) {
        super("Could not validate. Token " + token + " is invalid.");
    }

}
