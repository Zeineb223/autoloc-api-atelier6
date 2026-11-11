
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.ClientDTO;
import tn.esprit.autoloc.entity.Client;
import tn.esprit.autoloc.mapper.ClientMapper;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Gestion des clients AutoLoc")
public class ClientController {

    private final IClientService clientService;
    private final ClientMapper clientMapper;

    public ClientController(IClientService clientService,
                            ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    // POST : Ajouter un client
    @Operation(summary = "Ajouter un nouveau client")
    @ApiResponse(responseCode = "201", description = "Client créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO ajouter(@Valid @RequestBody ClientDTO dto) {

        Client client = clientMapper.toEntity(dto);
        client.setIdClient(null);

        Client clientEnregistre = clientService.enregistrerClient(client);

        return clientMapper.toDTO(clientEnregistre);
    }

    // GET : Afficher tous les clients
    @Operation(summary = "Afficher tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste des clients")
    @GetMapping
    public List<ClientDTO> afficherTous() {

        return clientService.afficherClients()
                .stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    // GET : Chercher un client par ID
    @Operation(summary = "Rechercher un client par identifiant")
    @ApiResponse(responseCode = "200", description = "Client trouvé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @GetMapping("/{id}")
    public ClientDTO chercher(@PathVariable Long id) {

        Client client = clientService.chercherClient(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Client introuvable avec ID : " + id
                ));

        return clientMapper.toDTO(client);
    }

    // PUT : Modifier un client
    @Operation(summary = "Modifier un client")
    @ApiResponse(responseCode = "200", description = "Client modifié")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @PutMapping("/{id}")
    public ClientDTO modifier(
            @PathVariable Long id,
            @Valid @RequestBody ClientDTO dto) {

        Client client = clientMapper.toEntity(dto);
        client.setIdClient(id);

        Client clientModifie = clientService.modifierClient(id, client);

        return clientMapper.toDTO(clientModifie);
    }

    // DELETE : Supprimer un client
    @Operation(summary = "Supprimer un client")
    @ApiResponse(responseCode = "204", description = "Client supprimé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {

        clientService.supprimerClient(id);
    }
}
