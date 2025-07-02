package com.planetpattounes.planetpattounes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.planetpattounes.planetpattounes.model.User;
import org.springframework.data.repository.query.Param;
import java.util.List;



public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u Where u.username = :username")
    public User getUserByUsername(@Param("username") String username);

}
