
package tn.esprit.autoloc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;

    private String ville;

    private String adresse;

    private String telephone;

    // Une agence possède plusieurs véhicules
    @OneToMany(mappedBy = "agence")
    private List<Vehicule> vehicules;

    // Une agence possède plusieurs employés
    @OneToMany(mappedBy = "agence")
    private List<Employe> employes;

}
