package com.planetpattounes.planetpattounes.repository;

import com.planetpattounes.planetpattounes.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a WHERE a.race = :race AND a.city = :city")
    Page<Animal> findByResearch(@Param("race") String race, @Param("city") String city, Pageable pageable);

    @Query("SELECT a FROM Animal a JOIN Association asso ON a.association_id = asso.id WHERE asso.user_id = :userId")
    List<Animal> findByAssoCreatorId(long userId);

    @Query("SELECT a FROM Animal a JOIN Association asso ON a.association_id = asso.id WHERE a.id = :animalId AND asso.user_id = :userId")
    Optional<Animal> findByAnimalIdAndAssoCreatorId(long animalId, long userId);
}