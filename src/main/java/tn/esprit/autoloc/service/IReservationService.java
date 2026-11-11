
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Reservation;
import java.util.List;
import java.util.Optional;

public interface IReservationService {

    Reservation enregistrerReservation(Reservation reservation);

    List<Reservation> afficherReservations();

    Optional<Reservation> chercherReservation(Long id);

    void supprimerReservation(Long id);
}
