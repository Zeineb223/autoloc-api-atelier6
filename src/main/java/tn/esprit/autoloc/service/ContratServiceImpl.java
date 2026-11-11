
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc.entity.Contrat;
import tn.esprit.autoloc.entity.Paiement;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IContratRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    public ContratServiceImpl(IContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    @Transactional
    public Contrat enregistrerContrat(Contrat contrat) {

        // Associer chaque paiement au contrat
        if (contrat.getPaiements() != null) {
            for (Paiement paiement : contrat.getPaiements()) {
                paiement.setContrat(contrat);
            }
        }

        // Enregistrer le contrat et ses paiements
        return contratRepository.save(contrat);
    }

    @Override
    public List<Contrat> afficherContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Optional<Contrat> chercherContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Contrat introuvable avec l'identifiant : " + id
            );
        }
        return contratRepository.findById(id);
    }

    @Override
    public void supprimerContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Impossible de supprimer : contrat introuvable avec l'identifiant : " + id
            );
        }
        contratRepository.deleteById(id);
    }
}
