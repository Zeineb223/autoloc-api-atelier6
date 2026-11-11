
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.entity.Contrat;
import tn.esprit.autoloc.mapper.ContratMapper;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrats", description = "Gestion des contrats de location")
public class ContratController {

    private final IContratService contratService;
    private final ContratMapper contratMapper;

    public ContratController(IContratService contratService,
                             ContratMapper contratMapper) {
        this.contratService = contratService;
        this.contratMapper = contratMapper;
    }

    @Operation(summary = "Créer un nouveau contrat")
    @ApiResponse(responseCode = "201", description = "Contrat créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDTO ajouter(@Valid @RequestBody ContratDTO dto) {

        Contrat contrat = contratMapper.toEntity(dto);

        return contratMapper.toDTO(
                contratService.enregistrerContrat(contrat)
        );
    }

    @Operation(summary = "Afficher tous les contrats")
    @ApiResponse(responseCode = "200", description = "Liste des contrats")
    @GetMapping
    public List<ContratDTO> afficherTous() {

        return contratService.afficherContrats()
                .stream()
                .map(contratMapper::toDTO)
                .toList();
    }

    @Operation(summary = "Rechercher un contrat par identifiant")
    @ApiResponse(responseCode = "200", description = "Contrat trouvé")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    @GetMapping("/{id}")
    public ContratDTO chercher(@PathVariable Long id) {

        return contratService.chercherContrat(id)
                .map(contratMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contrat introuvable avec ID : " + id
                ));
    }

    @Operation(summary = "Modifier un contrat")
    @ApiResponse(responseCode = "200", description = "Contrat modifié")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    @PutMapping("/{id}")
    public ContratDTO modifier(@PathVariable Long id,
                               @Valid @RequestBody ContratDTO dto) {

        Contrat contratExistant = contratService.chercherContrat(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contrat introuvable avec ID : " + id
                ));

        Contrat contrat = contratMapper.toEntity(dto);
        contrat.setIdContrat(id);

        // Préserver les paiements existants lors de la modification.
        contrat.setPaiements(contratExistant.getPaiements());

        return contratMapper.toDTO(
                contratService.enregistrerContrat(contrat)
        );
    }

    @Operation(summary = "Supprimer un contrat")
    @ApiResponse(responseCode = "204", description = "Contrat supprimé")
    @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {

        contratService.supprimerContrat(id);
    }
}
