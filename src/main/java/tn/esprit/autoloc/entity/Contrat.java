
package tn.esprit.autoloc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;

    private BigDecimal montantTotal;

    private boolean valide;

    // Relation avec Reservation
    @OneToOne
    @JoinColumn(name = "reservation_id", unique = true)
    private Reservation reservation;

    // Relation avec Paiement
    @OneToMany(
            mappedBy = "contrat",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference("contrat-paiements")
    private List<Paiement> paiements;

}
