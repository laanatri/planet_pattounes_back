package com.planetpattounes.planetpattounes.dto;

import com.planetpattounes.planetpattounes.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwtToken;
    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String description;
    private String city;
    private Role role;
}
