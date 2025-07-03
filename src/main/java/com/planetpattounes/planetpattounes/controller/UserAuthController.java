package com.planetpattounes.planetpattounes.controller;

import com.planetpattounes.planetpattounes.dto.AuthResponse;
import com.planetpattounes.planetpattounes.model.AuthRequest;
import com.planetpattounes.planetpattounes.model.User;
import com.planetpattounes.planetpattounes.service.JwtService;
import com.planetpattounes.planetpattounes.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
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
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            User user = userInfoService.getUserByUsername(authRequest.getUsername());
            return new AuthResponse(
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
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}
