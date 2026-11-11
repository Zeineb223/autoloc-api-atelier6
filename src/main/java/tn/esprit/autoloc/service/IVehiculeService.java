
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Vehicule;
import java.util.List;
import java.util.Optional;

public interface IVehiculeService {

    Vehicule enregistrerVehicule(Vehicule vehicule);

    List<Vehicule> afficherVehicules();

    Optional<Vehicule> chercherVehicule(Long id);

    Vehicule modifierVehicule(Long id, Vehicule vehicule);

    void supprimerVehicule(Long id);
}
