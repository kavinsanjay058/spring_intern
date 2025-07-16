package com.example.Springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    //    This is a Data Transfer Object (DTO) that is used to send a structured response from your authentication endpoint after a user logs in successfully.
    private String token;
    private String username;
    private String roles;
}

// Purpose	Response object after successful login (JWT + metadata)