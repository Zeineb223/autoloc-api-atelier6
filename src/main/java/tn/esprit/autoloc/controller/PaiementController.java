
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.PaiementDTO;
import tn.esprit.autoloc.mapper.PaiementMapper;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Paiements", description = "Consultation des paiements AutoLoc")
public class PaiementController {

    private final IPaiementService paiementService;
    private final PaiementMapper paiementMapper;

    public PaiementController(IPaiementService paiementService,
                              PaiementMapper paiementMapper) {
        this.paiementService = paiementService;
        this.paiementMapper = paiementMapper;
    }

    @Operation(summary = "Afficher tous les paiements")
    @ApiResponse(
            responseCode = "200",
            description = "Liste des paiements"
    )
    @GetMapping
    public List<PaiementDTO> afficherTous() {

        return paiementService.afficherPaiements()
                .stream()
                .map(paiementMapper::toDTO)
                .toList();
    }

    @Operation(summary = "Rechercher un paiement par identifiant")
    @ApiResponse(
            responseCode = "200",
            description = "Paiement trouvé"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Paiement introuvable"
    )
    @GetMapping("/{id}")
    public PaiementDTO chercher(@PathVariable Long id) {

        return paiementService.chercherPaiement(id)
                .map(paiementMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paiement introuvable avec ID : " + id
                ));
    }
}

