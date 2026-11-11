
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entity.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    public AgenceServiceImpl(IAgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public Agence enregistrerAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public List<Agence> afficherAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Optional<Agence> chercherAgence(Long id) {
        return agenceRepository.findById(id);
    }

    @Override
    public void supprimerAgence(Long id) {
        agenceRepository.deleteById(id);
    }
}
