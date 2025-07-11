package com.planetpattounes.planetpattounes.service;

import com.planetpattounes.planetpattounes.model.Association;
import com.planetpattounes.planetpattounes.repository.AssociationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssociationServiceImpl implements AssociationService {
    private AssociationRepository associationRepository;

    @Override
    public Association creer(Association association) {
        if(association.getUser_id() == null) {
            throw  new IllegalArgumentException("id utilisateur manquant");
        }
        if (associationRepository.findByUser_id(association.getUser_id()).isPresent()) {
            throw new IllegalStateException("Cet utilisateur a déjà créé une association");
        }
        association.setId(null);
        return associationRepository.save(association);
    }

    @Override
    public List<Association> lire() {
        return associationRepository.findAll();
    }

    @Override
    public Association lireParIdUtilisateur(Long user_id) {
        return associationRepository.findByUser_id(user_id).orElseThrow(() -> new RuntimeException("Aucune association pour cet utilisateur " + user_id));
    }

    @Override
    public Optional<Association> modifier(Long id, Association association) {
        return associationRepository.findById(id)
                .map(a -> {
                    if (association.getName() != null && !association.getName().trim().isEmpty()) {
                        a.setName(association.getName());
                    }
                    if (association.getCity().equals(association.getCity())) {
                        a.setCity(association.getCity());
                    }
                    if (association.getDescription().equals(association.getDescription())) {
                        a.setDescription(association.getDescription());
                    }
                    return associationRepository.save(a);
                });
    }

    @Override
    public String supprimer(Long id) {
        if (!associationRepository.existsById(id)) {
            throw new RuntimeException("Cet association pour cet utilisateur " + id + " n'existe pas");
        }
        associationRepository.deleteById(id);
        return "Association supprimée";
    }

}