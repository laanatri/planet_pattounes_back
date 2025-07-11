package com.planetpattounes.planetpattounes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.planetpattounes.planetpattounes.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}