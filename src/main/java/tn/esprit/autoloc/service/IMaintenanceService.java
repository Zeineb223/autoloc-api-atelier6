
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Maintenance;
import java.util.List;
import java.util.Optional;

public interface IMaintenanceService {

    Maintenance enregistrerMaintenance(Maintenance maintenance);

    List<Maintenance> afficherMaintenances();

    Optional<Maintenance> chercherMaintenance(Long id);

    void supprimerMaintenance(Long id);
}
