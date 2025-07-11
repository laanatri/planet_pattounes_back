package com.planetpattounes.planetpattounes.controller;

import com.planetpattounes.planetpattounes.model.Association;
import com.planetpattounes.planetpattounes.service.AssociationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/association")
@AllArgsConstructor
public class AssociationController {
    private final AssociationService associationService;

    @PostMapping("/create")
    public Association create(@RequestBody Association association) {
        return associationService.creer(association);
    }

    @GetMapping("/read")
    public List<Association> read() {
        return associationService.lire();
    }

    @GetMapping("/read/{user_id}")
    public Association read(@PathVariable("user_id") Long user_id) {
        return associationService.lireParIdUtilisateur(user_id);
    }

    @PatchMapping("/update/{user_id}")
    public Association update(@RequestBody Association association, @PathVariable("user_id") Long user_id) {
        return associationService.modifier(user_id, association)
                .map(updateAssociation -> ResponseEntity.ok().body(updateAssociation))
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    @DeleteMapping("/delete/{user_id}")
    public String delete(@PathVariable("user_id") Long user_id) {
        return associationService.supprimer(user_id);
    }

    // Voir pour delete aussi les animaux de cette asso pour que ce soit en cascade
    // Check pour les migrations / seeds
}