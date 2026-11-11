
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.mapper.VehiculeMapper;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@Tag(name = "Véhicules", description = "Gestion des véhicules AutoLoc")
public class VehiculeController {

    private final IVehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    public VehiculeController(IVehiculeService vehiculeService,
                              VehiculeMapper vehiculeMapper) {
        this.vehiculeService = vehiculeService;
        this.vehiculeMapper = vehiculeMapper;
    }

    @Operation(summary = "Ajouter un nouveau véhicule")
    @ApiResponse(responseCode = "201", description = "Véhicule créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDTO ajouter(@Valid @RequestBody VehiculeDTO dto) {
        return vehiculeMapper.toDTO(
                vehiculeService.enregistrerVehicule(
                        vehiculeMapper.toEntity(dto)
                )
        );
    }

    @Operation(summary = "Afficher tous les véhicules")
    @ApiResponse(responseCode = "200", description = "Liste des véhicules")
    @GetMapping
    public List<VehiculeDTO> afficherTous() {
        return vehiculeService.afficherVehicules()
                .stream()
                .map(vehiculeMapper::toDTO)
                .toList();
    }

    @Operation(summary = "Rechercher un véhicule par identifiant")
    @ApiResponse(responseCode = "200", description = "Véhicule trouvé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @GetMapping("/{id}")
    public VehiculeDTO chercher(@PathVariable Long id) {
        return vehiculeService.chercherVehicule(id)
                .map(vehiculeMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Véhicule introuvable avec ID : " + id
                ));
    }

    @Operation(summary = "Modifier un véhicule")
    @ApiResponse(responseCode = "200", description = "Véhicule modifié")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @PutMapping("/{id}")
    public VehiculeDTO modifier(@PathVariable Long id,
                                @Valid @RequestBody VehiculeDTO dto) {
        return vehiculeMapper.toDTO(
                vehiculeService.modifierVehicule(
                        id,
                        vehiculeMapper.toEntity(dto)
                )
        );
    }

    @Operation(summary = "Supprimer un véhicule")
    @ApiResponse(responseCode = "204", description = "Véhicule supprimé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
    }
}
