package com.planetpattounes.planetpattounes.service;

import com.planetpattounes.planetpattounes.model.Association;

import java.util.List;
import java.util.Optional;

public interface AssociationService {

    Association creer(Association association);

    List<Association> lire();

    Association lireParIdUtilisateur(Long user_id);

    Optional<Association> modifier(Long id, Association association);

    String supprimer(Long id);

}
