package com.planetpattounes.planetpattounes.controller;

import com.planetpattounes.planetpattounes.model.Animal;
import com.planetpattounes.planetpattounes.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animal")
@AllArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping("/create")
    public Animal create(@RequestBody Animal animal) {
        return animalService.creer(animal);
    }

    // A tester
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<Page<Animal>>  search(@RequestParam String race, @RequestParam String city, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer perPage) {
        Page<Animal> animals = animalService.search(race, city, page, perPage);
        return  ResponseEntity.ok(animals);
    }

    @GetMapping("/read/{user_id}")
    public List<Animal> read(@PathVariable Long user_id) {
        return animalService.lire(user_id);
    }

    // A tester
    @GetMapping("/read/{id}/{user_id}")
    public Optional<Animal> read(@PathVariable Long id, @PathVariable Long user_id) {
        return animalService.lireParId(id, user_id);
    }

    @PatchMapping("/update/{id}")
    public Animal update(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.modifier(id, animal)
                .map(updateAnimal -> ResponseEntity.ok().body(updateAnimal))
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return animalService.supprimer(id);
    }

}