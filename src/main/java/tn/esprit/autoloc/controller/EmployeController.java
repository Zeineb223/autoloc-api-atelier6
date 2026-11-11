
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.autoloc.dto.EmployeDTO;
import tn.esprit.autoloc.mapper.EmployeMapper;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@Tag(name = "Employés", description = "Gestion des employés AutoLoc")
public class EmployeController {

    private final IEmployeService employeService;
    private final EmployeMapper employeMapper;

    public EmployeController(IEmployeService employeService,
                             EmployeMapper employeMapper) {
        this.employeService = employeService;
        this.employeMapper = employeMapper;
    }

    @Operation(summary = "Ajouter un nouvel employé")
    @ApiResponse(responseCode = "201", description = "Employé créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeDTO ajouter(@Valid @RequestBody EmployeDTO dto) {
        return employeMapper.toDTO(
                employeService.enregistrerEmploye(
                        employeMapper.toEntity(dto)
                )
        );
    }

    @Operation(summary = "Afficher tous les employés")
    @ApiResponse(responseCode = "200", description = "Liste des employés")
    @GetMapping
    public List<EmployeDTO> afficherTous() {
        return employeService.afficherEmployes()
                .stream()
                .map(employeMapper::toDTO)
                .toList();
    }

    @Operation(summary = "Rechercher un employé par identifiant")
    @ApiResponse(responseCode = "200", description = "Employé trouvé")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    @GetMapping("/{id}")
    public EmployeDTO chercher(@PathVariable Long id) {
        return employeService.chercherEmploye(id)
                .map(employeMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employé introuvable avec ID : " + id
                ));
    }

    @Operation(summary = "Modifier un employé")
    @ApiResponse(responseCode = "200", description = "Employé modifié")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    @PutMapping("/{id}")
    public EmployeDTO modifier(@PathVariable Long id,
                               @Valid @RequestBody EmployeDTO dto) {

        if (employeService.chercherEmploye(id).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employé introuvable avec ID : " + id
            );
        }

        var employe = employeMapper.toEntity(dto);
        employe.setIdEmploye(id);

        return employeMapper.toDTO(
                employeService.enregistrerEmploye(employe)
        );
    }

    @Operation(summary = "Supprimer un employé")
    @ApiResponse(responseCode = "204", description = "Employé supprimé")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        employeService.supprimerEmploye(id);
    }
}
