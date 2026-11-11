
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entity.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    public ReservationServiceImpl(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation enregistrerReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> afficherReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> chercherReservation(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void supprimerReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
