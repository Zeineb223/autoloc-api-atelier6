
package tn.esprit.autoloc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import tn.esprit.autoloc.enums.CategorieVehicule;
import tn.esprit.autoloc.enums.StatutVehicule;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    private String immatriculation;

    private String marque;

    private String modele;

    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;

    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    // Relation avec Agence
    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

    // Relation avec Reservation
    @OneToMany(mappedBy = "vehicule")
    private List<Reservation> reservations;

    // Relation avec Maintenance
    @OneToMany(mappedBy = "vehicule")
    private List<Maintenance> maintenances;

    // Relation avec Equipement
    @ManyToMany
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    private List<Equipement> equipements;

}
