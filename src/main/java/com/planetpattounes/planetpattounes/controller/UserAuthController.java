package com.planetpattounes.planetpattounes.controller;

import com.planetpattounes.planetpattounes.dto.AuthResponse;
import com.planetpattounes.planetpattounes.model.AuthRequest;
import com.planetpattounes.planetpattounes.model.User;
import com.planetpattounes.planetpattounes.service.JwtService;
import com.planetpattounes.planetpattounes.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserInfoService userInfoService;
    private final JwtService  jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to PlanetPattounes!";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                User user = userInfoService.getUserByUsername(authRequest.getUsername());
                AuthResponse authResponse = new AuthResponse(
                        token,
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getDescription(),
                        user.getCity(),
                        user.getRole()
                );
                return ResponseEntity.ok(authResponse);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Authentication."));

        } catch (UsernameNotFoundException | BadCredentialsException e) {
            System.err.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Pseudo ou mot de passe invalide."));
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during authentication: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Erreur serveur."));
        }
    }

}
