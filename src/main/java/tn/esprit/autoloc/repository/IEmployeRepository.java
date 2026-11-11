
package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entity.Employe;

public interface IEmployeRepository extends JpaRepository<Employe, Long> {

}
