
package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipementDTO {

    private Long idEquipement;

    @NotBlank(message = "Le libellé est obligatoire")
    private String libelle;

    private List<Long> idsVehicules;
}
