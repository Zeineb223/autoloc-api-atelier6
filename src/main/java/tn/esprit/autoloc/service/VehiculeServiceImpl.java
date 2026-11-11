
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entity.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    public VehiculeServiceImpl(IVehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Vehicule enregistrerVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> afficherVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Optional<Vehicule> chercherVehicule(Long id) {
        return vehiculeRepository.findById(id);
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehicule) {
        Vehicule existant = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicule introuvable"));

        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());

        return vehiculeRepository.save(existant);
    }

    @Override
    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
