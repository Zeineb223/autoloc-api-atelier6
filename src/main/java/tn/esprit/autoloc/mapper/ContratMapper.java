
package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.entity.Contrat;
import tn.esprit.autoloc.entity.Paiement;
import tn.esprit.autoloc.entity.Reservation;

@Component
public class ContratMapper {

    public ContratDTO toDTO(Contrat contrat) {

        if (contrat == null) {
            return null;
        }

        ContratDTO dto = new ContratDTO();

        dto.setIdContrat(contrat.getIdContrat());
        dto.setDateSignature(contrat.getDateSignature());
        dto.setMontantTotal(contrat.getMontantTotal());
        dto.setValide(contrat.isValide());

        if (contrat.getReservation() != null) {
            dto.setIdReservation(
                    contrat.getReservation().getIdReservation()
            );
        }

        if (contrat.getPaiements() != null) {
            dto.setIdsPaiements(
                    contrat.getPaiements()
                            .stream()
                            .map(Paiement::getIdPaiement)
                            .toList()
            );
        }

        return dto;
    }

    public Contrat toEntity(ContratDTO dto) {

        if (dto == null) {
            return null;
        }

        Contrat contrat = new Contrat();

        contrat.setIdContrat(dto.getIdContrat());
        contrat.setDateSignature(dto.getDateSignature());
        contrat.setMontantTotal(dto.getMontantTotal());
        contrat.setValide(dto.isValide());

        if (dto.getIdReservation() != null) {
            Reservation reservation = new Reservation();
            reservation.setIdReservation(dto.getIdReservation());
            contrat.setReservation(reservation);
        }

        // Les paiements existants ne sont pas reconstruits
        // depuis une simple liste d'identifiants.

        return contrat;
    }
}
