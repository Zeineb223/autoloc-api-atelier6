
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.EquipementDTO;
import tn.esprit.autoloc.entity.Equipement;
import tn.esprit.autoloc.mapper.EquipementMapper;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@Tag(name = "Équipements", description = "Gestion des équipements AutoLoc")
public class EquipementController {

    private final IEquipementService equipementService;
    private final EquipementMapper equipementMapper;

    public EquipementController(IEquipementService equipementService,
                                EquipementMapper equipementMapper) {
        this.equipementService = equipementService;
        this.equipementMapper = equipementMapper;
    }

    @Operation(summary = "Ajouter un nouvel équipement")
    @ApiResponse(responseCode = "201", description = "Équipement créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDTO ajouter(@Valid @RequestBody EquipementDTO dto) {

        return equipementMapper.toDTO(
                equipementService.enregistrerEquipement(
                        equipementMapper.toEntity(dto)
                )
        );
    }

    @Operation(summary = "Afficher tous les équipements")
    @ApiResponse(responseCode = "200", description = "Liste des équipements")
    @GetMapping
    public List<EquipementDTO> afficherTous() {

        return equipementService.afficherEquipements()
                .stream()
                .map(equipementMapper::toDTO)
                .toList();
    }

    @Operation(summary = "Rechercher un équipement par identifiant")
    @ApiResponse(responseCode = "200", description = "Équipement trouvé")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    @GetMapping("/{id}")
    public EquipementDTO chercher(@PathVariable Long id) {

        return equipementService.chercherEquipement(id)
                .map(equipementMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Équipement introuvable avec ID : " + id
                ));
    }

    @Operation(summary = "Modifier un équipement")
    @ApiResponse(responseCode = "200", description = "Équipement modifié")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    @PutMapping("/{id}")
    public EquipementDTO modifier(@PathVariable Long id,
                                  @Valid @RequestBody EquipementDTO dto) {

        Equipement equipementExistant =
                equipementService.chercherEquipement(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Équipement introuvable avec ID : " + id
                        ));

        Equipement equipement = equipementMapper.toEntity(dto);
        equipement.setIdEquipement(id);

        // Conserver les relations existantes avec les véhicules.
        equipement.setVehicules(equipementExistant.getVehicules());

        return equipementMapper.toDTO(
                equipementService.enregistrerEquipement(equipement)
        );
    }

    @Operation(summary = "Supprimer un équipement")
    @ApiResponse(responseCode = "204", description = "Équipement supprimé")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {

        equipementService.supprimerEquipement(id);
    }
}
