
package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.dto.ClientDTO;
import tn.esprit.autoloc.entity.Client;

@Component
public class ClientMapper {

    // Convertir Client en ClientDTO
    public ClientDTO toDTO(Client client) {

        if (client == null) {
            return null;
        }

        ClientDTO dto = new ClientDTO();

        dto.setIdClient(client.getIdClient());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setEmail(client.getEmail());
        dto.setTelephone(client.getTelephone());
        dto.setNumPermis(client.getNumPermis());
        dto.setDateInscription(client.getDateInscription());

        return dto;
    }

    // Convertir ClientDTO en Client
    public Client toEntity(ClientDTO dto) {

        if (dto == null) {
            return null;
        }

        Client client = new Client();

        client.setIdClient(dto.getIdClient());
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setNumPermis(dto.getNumPermis());
        client.setDateInscription(dto.getDateInscription());

        return client;
    }
}
