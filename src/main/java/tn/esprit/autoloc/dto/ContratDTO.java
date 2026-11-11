
package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratDTO {

    private Long idContrat;

    @NotNull(message = "La date de signature est obligatoire")
    private LocalDate dateSignature;

    @NotNull(message = "Le montant total est obligatoire")
    @DecimalMin(value = "0.0", message = "Le montant doit être positif ou nul")
    private BigDecimal montantTotal;

    private boolean valide;

    @NotNull(message = "La réservation est obligatoire")
    private Long idReservation;

    private List<Long> idsPaiements;
}
