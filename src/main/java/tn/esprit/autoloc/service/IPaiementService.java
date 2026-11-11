
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Paiement;
import java.util.List;
import java.util.Optional;

public interface IPaiementService {

    List<Paiement> afficherPaiements();

    Optional<Paiement> chercherPaiement(Long id);
}
