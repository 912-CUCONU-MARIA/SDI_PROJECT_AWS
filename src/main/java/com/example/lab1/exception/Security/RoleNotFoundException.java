package com.example.lab1.exception.Security;

import com.example.lab1.model.Security.ERole;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(ERole role) {
        super("Could not find role " + role);
    }

}