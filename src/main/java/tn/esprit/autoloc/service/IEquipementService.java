
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Equipement;
import java.util.List;
import java.util.Optional;

public interface IEquipementService {

    Equipement enregistrerEquipement(Equipement equipement);

    List<Equipement> afficherEquipements();

    Optional<Equipement> chercherEquipement(Long id);

    void supprimerEquipement(Long id);
}
