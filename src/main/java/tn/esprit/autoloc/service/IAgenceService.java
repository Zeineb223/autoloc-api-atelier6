
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Agence;

import java.util.List;
import java.util.Optional;

public interface IAgenceService {

    Agence enregistrerAgence(Agence agence);

    List<Agence> afficherAgences();

    Optional<Agence> chercherAgence(Long id);

    void supprimerAgence(Long id);
}
