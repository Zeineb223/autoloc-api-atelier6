
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.ReservationDTO;
import tn.esprit.autoloc.mapper.ReservationMapper;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Réservations", description = "Gestion des réservations AutoLoc")
public class ReservationController {

    private final IReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(IReservationService reservationService,
                                 ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @Operation(summary = "Ajouter une nouvelle réservation")
    @ApiResponse(responseCode = "201", description = "Réservation créée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDTO ajouter(@Valid @RequestBody ReservationDTO dto) {

        return reservationMapper.toDTO(
                reservationService.enregistrerReservation(
                        reservationMapper.toEntity(dto)
                )
        );
    }

    @Operation(summary = "Afficher toutes les réservations")
    @ApiResponse(responseCode = "200", description = "Liste des réservations")
    @GetMapping
    public List<ReservationDTO> afficherTous() {

        return reservationService.afficherReservations()
                .stream()
                .map(reservationMapper::toDTO)
                .toList();
    }

    @Operation(summary = "Rechercher une réservation par identifiant")
    @ApiResponse(responseCode = "200", description = "Réservation trouvée")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    @GetMapping("/{id}")
    public ReservationDTO chercher(@PathVariable Long id) {

        return reservationService.chercherReservation(id)
                .map(reservationMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Réservation introuvable avec ID : " + id
                ));
    }

    @Operation(summary = "Modifier une réservation")
    @ApiResponse(responseCode = "200", description = "Réservation modifiée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    @PutMapping("/{id}")
    public ReservationDTO modifier(@PathVariable Long id,
                                   @Valid @RequestBody ReservationDTO dto) {

        if (reservationService.chercherReservation(id).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Réservation introuvable avec ID : " + id
            );
        }

        var reservation = reservationMapper.toEntity(dto);
        reservation.setIdReservation(id);

        return reservationMapper.toDTO(
                reservationService.enregistrerReservation(reservation)
        );
    }

    @Operation(summary = "Supprimer une réservation")
    @ApiResponse(responseCode = "204", description = "Réservation supprimée")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {

        reservationService.supprimerReservation(id);
    }
}
