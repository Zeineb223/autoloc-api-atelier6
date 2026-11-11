
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Contrat;
import java.util.List;
import java.util.Optional;

public interface IContratService {

    Contrat enregistrerContrat(Contrat contrat);

    List<Contrat> afficherContrats();

    Optional<Contrat> chercherContrat(Long id);

    void supprimerContrat(Long id);
}
