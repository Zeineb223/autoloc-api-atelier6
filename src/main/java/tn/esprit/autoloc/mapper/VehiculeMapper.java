
package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.entity.Vehicule;

@Component
public class VehiculeMapper {

    public VehiculeDTO toDTO(Vehicule vehicule) {

        if (vehicule == null) {
            return null;
        }

        VehiculeDTO dto = new VehiculeDTO();

        dto.setIdVehicule(vehicule.getIdVehicule());
        dto.setImmatriculation(vehicule.getImmatriculation());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setCategorie(vehicule.getCategorie());
        dto.setTarifJournalier(vehicule.getTarifJournalier());
        dto.setStatut(vehicule.getStatut());

        return dto;
    }

    public Vehicule toEntity(VehiculeDTO dto) {

        if (dto == null) {
            return null;
        }

        Vehicule vehicule = new Vehicule();

        vehicule.setIdVehicule(dto.getIdVehicule());
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setCategorie(dto.getCategorie());
        vehicule.setTarifJournalier(dto.getTarifJournalier());
        vehicule.setStatut(dto.getStatut());

        return vehicule;
    }
}
