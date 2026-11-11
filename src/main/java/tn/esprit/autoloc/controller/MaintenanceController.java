
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.MaintenanceDTO;
import tn.esprit.autoloc.entity.Maintenance;
import tn.esprit.autoloc.mapper.MaintenanceMapper;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@Tag(name = "Maintenances", description = "Gestion des maintenances AutoLoc")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceController(IMaintenanceService maintenanceService,
                                 MaintenanceMapper maintenanceMapper) {
        this.maintenanceService = maintenanceService;
        this.maintenanceMapper = maintenanceMapper;
    }

    @Operation(summary = "Ajouter une nouvelle maintenance")
    @ApiResponse(responseCode = "201", description = "Maintenance créée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO ajouter(@Valid @RequestBody MaintenanceDTO dto) {

        return maintenanceMapper.toDTO(
                maintenanceService.enregistrerMaintenance(
                        maintenanceMapper.toEntity(dto)
                )
        );
    }

    @Operation(summary = "Afficher toutes les maintenances")
    @ApiResponse(responseCode = "200", description = "Liste des maintenances")
    @GetMapping
    public List<MaintenanceDTO> afficherTous() {

        return maintenanceService.afficherMaintenances()
                .stream()
                .map(maintenanceMapper::toDTO)
                .toList();
    }

    @Operation(summary = "Rechercher une maintenance par identifiant")
    @ApiResponse(responseCode = "200", description = "Maintenance trouvée")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    @GetMapping("/{id}")
    public MaintenanceDTO chercher(@PathVariable Long id) {

        return maintenanceService.chercherMaintenance(id)
                .map(maintenanceMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Maintenance introuvable avec ID : " + id
                ));
    }

    @Operation(summary = "Modifier une maintenance")
    @ApiResponse(responseCode = "200", description = "Maintenance modifiée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    @PutMapping("/{id}")
    public MaintenanceDTO modifier(@PathVariable Long id,
                                   @Valid @RequestBody MaintenanceDTO dto) {

        if (maintenanceService.chercherMaintenance(id).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Maintenance introuvable avec ID : " + id
            );
        }

        Maintenance maintenance = maintenanceMapper.toEntity(dto);
        maintenance.setIdMaintenance(id);

        return maintenanceMapper.toDTO(
                maintenanceService.enregistrerMaintenance(maintenance)
        );
    }

    @Operation(summary = "Supprimer une maintenance")
    @ApiResponse(responseCode = "204", description = "Maintenance supprimée")
    @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {

        maintenanceService.supprimerMaintenance(id);
    }
}
