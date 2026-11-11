
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entity.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    public EquipementServiceImpl(IEquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @Override
    public Equipement enregistrerEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public List<Equipement> afficherEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Optional<Equipement> chercherEquipement(Long id) {
        return equipementRepository.findById(id);
    }

    @Override
    public void supprimerEquipement(Long id) {
        equipementRepository.deleteById(id);
    }
}
