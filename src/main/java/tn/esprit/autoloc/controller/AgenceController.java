
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.AgenceDTO;
import tn.esprit.autoloc.mapper.AgenceMapper;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@Tag(name = "Agences", description = "Gestion des agences AutoLoc")
public class AgenceController {

    private final IAgenceService agenceService;
    private final AgenceMapper agenceMapper;

    public AgenceController(IAgenceService agenceService,
                            AgenceMapper agenceMapper) {
        this.agenceService = agenceService;
        this.agenceMapper = agenceMapper;
    }

    @Operation(summary = "Ajouter une nouvelle agence")
    @ApiResponse(responseCode = "201", description = "Agence créée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDTO ajouter(@Valid @RequestBody AgenceDTO dto) {
        return agenceMapper.toDTO(
                agenceService.enregistrerAgence(
                        agenceMapper.toEntity(dto)
                )
        );
    }

    @Operation(summary = "Afficher toutes les agences")
    @ApiResponse(responseCode = "200", description = "Liste des agences")
    @GetMapping
    public List<AgenceDTO> afficherTous() {
        return agenceService.afficherAgences()
                .stream()
                .map(agenceMapper::toDTO)
                .toList();
    }

    @Operation(summary = "Rechercher une agence par identifiant")
    @ApiResponse(responseCode = "200", description = "Agence trouvée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @GetMapping("/{id}")
    public AgenceDTO chercher(@PathVariable Long id) {
        return agenceService.chercherAgence(id)
                .map(agenceMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agence introuvable avec ID : " + id
                ));
    }

    @Operation(summary = "Modifier une agence")
    @ApiResponse(responseCode = "200", description = "Agence modifiée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @PutMapping("/{id}")
    public AgenceDTO modifier(@PathVariable Long id,
                              @Valid @RequestBody AgenceDTO dto) {

        if (agenceService.chercherAgence(id).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Agence introuvable avec ID : " + id
            );
        }

        var agence = agenceMapper.toEntity(dto);
        agence.setIdAgence(id);

        return agenceMapper.toDTO(
                agenceService.enregistrerAgence(agence)
        );
    }

    @Operation(summary = "Supprimer une agence")
    @ApiResponse(responseCode = "204", description = "Agence supprimée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        agenceService.supprimerAgence(id);
    }
}
