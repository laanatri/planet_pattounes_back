package com.planetpattounes.planetpattounes.service;

import com.planetpattounes.planetpattounes.model.User;
import com.planetpattounes.planetpattounes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User creer(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setId(null);
        return userRepository.save(user);
    }

    @Override
    public List<User> lire() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> modifier(Long id, User user) {
        return Optional.ofNullable(userRepository.findById(id)
                .map(u -> {
                    if (user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
                        u.setUsername(user.getUsername());
                    }
                    if (user.getEmail() != null && !user.getEmail().trim().isEmpty()) {
                        u.setEmail(user.getEmail());
                    }
                    if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
                        u.setPassword(passwordEncoder.encode(user.getPassword()));
                    }
                    if (user.getFirstname() != null && !user.getFirstname().trim().isEmpty()) {
                        u.setFirstname(user.getFirstname());
                    }
                    if (user.getLastname() != null && !user.getLastname().trim().isEmpty()) {
                        u.setLastname(user.getLastname());
                    }
                    if (user.getDescription() != null && !user.getDescription().trim().isEmpty()) {
                        u.setDescription(user.getDescription());
                    }
                    if (user.getCity() != null && !user.getCity().trim().isEmpty()) {
                        u.setCity(user.getCity());
                    }
                    return userRepository.save(u);
                }).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public String supprimer(Long id) {
        userRepository.deleteById(id);
        return "Deleted user !";
    }

}
