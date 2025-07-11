package com.planetpattounes.planetpattounes.service;

import com.planetpattounes.planetpattounes.model.Animal;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    Animal creer(Animal animal);

    Page<Animal> search(String race, String city, Integer page, Integer perPage);

    List<Animal> lire(Long user_id);

    Optional<Animal> lireParId(Long id, Long user_id);

    Optional<Animal> modifier(Long id, Animal animal);

    String supprimer(Long id);
}