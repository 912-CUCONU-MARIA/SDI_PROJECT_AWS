package com.example.lab1.model.dto.Security;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    String username;

    String jwtToken;

}