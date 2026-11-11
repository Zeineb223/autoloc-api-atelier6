
package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.dto.AgenceDTO;
import tn.esprit.autoloc.entity.Agence;

@Component
public class AgenceMapper {

    public AgenceDTO toDTO(Agence agence) {

        if (agence == null) {
            return null;
        }

        AgenceDTO dto = new AgenceDTO();

        dto.setIdAgence(agence.getIdAgence());
        dto.setNom(agence.getNom());
        dto.setVille(agence.getVille());
        dto.setAdresse(agence.getAdresse());
        dto.setTelephone(agence.getTelephone());

        return dto;
    }

    public Agence toEntity(AgenceDTO dto) {

        if (dto == null) {
            return null;
        }

        Agence agence = new Agence();

        agence.setIdAgence(dto.getIdAgence());
        agence.setNom(dto.getNom());
        agence.setVille(dto.getVille());
        agence.setAdresse(dto.getAdresse());
        agence.setTelephone(dto.getTelephone());

        return agence;
    }
}