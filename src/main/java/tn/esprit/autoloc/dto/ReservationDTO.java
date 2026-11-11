
package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import tn.esprit.autoloc.enums.StatutReservation;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long idReservation;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    @NotNull(message = "Le statut est obligatoire")
    private StatutReservation statut;

    @NotNull(message = "Le client est obligatoire")
    private Long idClient;

    @NotNull(message = "Le véhicule est obligatoire")
    private Long idVehicule;

    @AssertTrue(message = "La date de fin doit être postérieure ou égale à la date de début")
    public boolean isDatesValides() {
        return dateDebut == null
                || dateFin == null
                || !dateFin.isBefore(dateDebut);
    }
}
