package com.planetpattounes.planetpattounes.repository;

import com.planetpattounes.planetpattounes.model.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {
    @Query("SELECT a FROM Association a WHERE a.user_id = :userId")
    Optional<Association> findByUser_id(long userId);
}