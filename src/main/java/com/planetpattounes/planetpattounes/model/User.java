package com.planetpattounes.planetpattounes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String description;
    private String city;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private Role role;

}