package com.planetpattounes.planetpattounes.controller;

import com.planetpattounes.planetpattounes.model.User;
import com.planetpattounes.planetpattounes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.creer(user);
    }
    // check DOT et mapper (bonne pratique pour pas exposer des choses sensibles)

    @GetMapping("/read")
    public List<User> read() {
        return userService.lire();
    }

    @PatchMapping("/update/{id}")
    public User update(@PathVariable Long id,@RequestBody User user) {
        return userService.modifier(id, user)
                .map(updateUser -> ResponseEntity.ok().body(updateUser))
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return userService.supprimer(id);
    }

}