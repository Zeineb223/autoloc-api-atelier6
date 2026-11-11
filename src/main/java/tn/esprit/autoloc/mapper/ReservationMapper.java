
package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc.dto.ReservationDTO;
import tn.esprit.autoloc.entity.Client;
import tn.esprit.autoloc.entity.Reservation;
import tn.esprit.autoloc.entity.Vehicule;

@Component
public class ReservationMapper {

    public ReservationDTO toDTO(Reservation reservation) {

        if (reservation == null) {
            return null;
        }

        ReservationDTO dto = new ReservationDTO();

        dto.setIdReservation(reservation.getIdReservation());
        dto.setDateDebut(reservation.getDateDebut());
        dto.setDateFin(reservation.getDateFin());
        dto.setStatut(reservation.getStatut());

        if (reservation.getClient() != null) {
            dto.setIdClient(reservation.getClient().getIdClient());
        }

        if (reservation.getVehicule() != null) {
            dto.setIdVehicule(reservation.getVehicule().getIdVehicule());
        }

        return dto;
    }

    public Reservation toEntity(ReservationDTO dto) {

        if (dto == null) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setIdReservation(dto.getIdReservation());
        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(dto.getStatut());

        if (dto.getIdClient() != null) {
            Client client = new Client();
            client.setIdClient(dto.getIdClient());
            reservation.setClient(client);
        }

        if (dto.getIdVehicule() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.getIdVehicule());
            reservation.setVehicule(vehicule);
        }

        return reservation;
    }
}