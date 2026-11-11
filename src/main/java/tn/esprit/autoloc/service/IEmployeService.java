
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Employe;
import java.util.List;
import java.util.Optional;

public interface IEmployeService {

    Employe enregistrerEmploye(Employe employe);

    List<Employe> afficherEmployes();

    Optional<Employe> chercherEmploye(Long id);

    void supprimerEmploye(Long id);
}
