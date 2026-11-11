
package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc.dto.EquipementDTO;
import tn.esprit.autoloc.entity.Equipement;
import tn.esprit.autoloc.entity.Vehicule;

@Component
public class EquipementMapper {

    public EquipementDTO toDTO(Equipement equipement) {

        if (equipement == null) {
            return null;
        }

        EquipementDTO dto = new EquipementDTO();

        dto.setIdEquipement(equipement.getIdEquipement());
        dto.setLibelle(equipement.getLibelle());

        if (equipement.getVehicules() != null) {
            dto.setIdsVehicules(
                    equipement.getVehicules()
                            .stream()
                            .map(Vehicule::getIdVehicule)
                            .toList()
            );
        }

        return dto;
    }

    public Equipement toEntity(EquipementDTO dto) {

        if (dto == null) {
            return null;
        }

        Equipement equipement = new Equipement();

        equipement.setIdEquipement(dto.getIdEquipement());
        equipement.setLibelle(dto.getLibelle());

        // Les relations avec les véhicules sont gérées
        // par le côté propriétaire de la relation ManyToMany.

        return equipement;
    }
}