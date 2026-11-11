
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entity.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    public PaiementServiceImpl(IPaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public List<Paiement> afficherPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Optional<Paiement> chercherPaiement(Long id) {
        return paiementRepository.findById(id);
    }
}
