package com.planetpattounes.planetpattounes.service;

import com.planetpattounes.planetpattounes.model.Animal;
import com.planetpattounes.planetpattounes.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private AnimalRepository animalRepository;

    @Override
    public Animal creer(Animal animal) {
        if(animal.getAssociation_id() == null) {
            throw new IllegalArgumentException("id asso manquant");
        }
        animal.setId(null);
        return animalRepository.save(animal);
    }

     @Override
     public Page<Animal> search(String race, String city, Integer page, Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return animalRepository.findByResearch(race, city, pageable);
     }

    @Override
    public List<Animal> lire(Long user_id) {
        return animalRepository.findByAssoCreatorId(user_id);
    }

    @Override
    public Optional<Animal> lireParId(Long id, Long user_id) {
        return animalRepository.findByAnimalIdAndAssoCreatorId(id, user_id);
    }

    @Override
    public Optional<Animal> modifier(Long id, Animal animal) {
        return animalRepository.findById(id)
                .map(a -> {
                    if (animal.getName() != null && !animal.getName().trim().isEmpty()) {
                        a.setName(animal.getName());
                    }
                    if (animal.getAge() == animal.getAge()) {
                        a.setAge(animal.getAge());
                    }
                    if (animal.getRace().equals(animal.getRace())) {
                        a.setRace(animal.getRace());
                    }
                    if (animal.getCity().equals(animal.getCity())) {
                        a.setCity(animal.getCity());
                    }
                    if (animal.getDescription().equals(animal.getDescription())) {
                        a.setDescription(animal.getDescription());
                    }
                    if (animal.getImage_url().equals(animal.getImage_url())) {
                        a.setImage_url(animal.getImage_url());
                    }
                    return animalRepository.save(a);
                });
    }

    @Override
    public String supprimer(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Aucune animal pour cet utilisateur " + id);
        }
        animalRepository.deleteById(id);
        return "animal supprimé";
    }

}