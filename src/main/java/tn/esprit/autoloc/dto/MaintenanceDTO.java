
package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDTO {

    private Long idMaintenance;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    private LocalDate dateFin;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotNull(message = "Le véhicule est obligatoire")
    private Long idVehicule;

    @AssertTrue(message = "La date de fin doit être postérieure ou égale à la date de début")
    public boolean isDatesValides() {
        return dateDebut == null
                || dateFin == null
                || !dateFin.isBefore(dateDebut);
    }
}
