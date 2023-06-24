package com.example.lab1.repository.Security;

import com.example.lab1.model.Security.UserJwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJwtRepository extends JpaRepository<UserJwt, Long> {

    Boolean existsByJwtToken(String jwtToken);

    Boolean existsByUsername(String username);

    UserJwt findByJwtToken(String jwtToken);

    void deleteByUsername(String username);

}