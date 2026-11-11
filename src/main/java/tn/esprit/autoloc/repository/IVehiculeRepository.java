
package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entity.Vehicule;

public interface IVehiculeRepository
        extends JpaRepository<Vehicule, Long> {

}
