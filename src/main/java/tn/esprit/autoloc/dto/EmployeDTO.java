
package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import tn.esprit.autoloc.enums.RoleEmploye;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO {

    private Long idEmploye;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotNull(message = "Le rôle est obligatoire")
    private RoleEmploye role;

    @NotNull(message = "L'agence est obligatoire")
    private Long idAgence;
}
