package com.planetpattounes.planetpattounes.service;

import com.planetpattounes.planetpattounes.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User creer(User user);

    List<User> lire();

    Optional<User> modifier(Long id, User user);

    String supprimer(Long id);

}
