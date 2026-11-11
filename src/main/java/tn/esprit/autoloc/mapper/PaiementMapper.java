
package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc.dto.PaiementDTO;
import tn.esprit.autoloc.entity.Contrat;
import tn.esprit.autoloc.entity.Paiement;

@Component
public class PaiementMapper {

    public PaiementDTO toDTO(Paiement paiement) {

        if (paiement == null) {
            return null;
        }

        PaiementDTO dto = new PaiementDTO();

        dto.setIdPaiement(paiement.getIdPaiement());
        dto.setMontant(paiement.getMontant());
        dto.setDatePaiement(paiement.getDatePaiement());
        dto.setModePaiement(paiement.getModePaiement());

        if (paiement.getContrat() != null) {
            dto.setIdContrat(
                    paiement.getContrat().getIdContrat()
            );
        }

        return dto;
    }

    public Paiement toEntity(PaiementDTO dto) {

        if (dto == null) {
            return null;
        }

        Paiement paiement = new Paiement();

        paiement.setIdPaiement(dto.getIdPaiement());
        paiement.setMontant(dto.getMontant());
        paiement.setDatePaiement(dto.getDatePaiement());
        paiement.setModePaiement(dto.getModePaiement());

        if (dto.getIdContrat() != null) {
            Contrat contrat = new Contrat();
            contrat.setIdContrat(dto.getIdContrat());
            paiement.setContrat(contrat);
        }

        return paiement;
    }
}