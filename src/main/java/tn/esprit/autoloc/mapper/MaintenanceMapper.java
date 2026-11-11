
package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc.dto.MaintenanceDTO;
import tn.esprit.autoloc.entity.Maintenance;
import tn.esprit.autoloc.entity.Vehicule;

@Component
public class MaintenanceMapper {

    public MaintenanceDTO toDTO(Maintenance maintenance) {

        if (maintenance == null) {
            return null;
        }

        MaintenanceDTO dto = new MaintenanceDTO();

        dto.setIdMaintenance(maintenance.getIdMaintenance());
        dto.setDateDebut(maintenance.getDateDebut());
        dto.setDateFin(maintenance.getDateFin());
        dto.setDescription(maintenance.getDescription());

        if (maintenance.getVehicule() != null) {
            dto.setIdVehicule(
                    maintenance.getVehicule().getIdVehicule()
            );
        }

        return dto;
    }

    public Maintenance toEntity(MaintenanceDTO dto) {

        if (dto == null) {
            return null;
        }

        Maintenance maintenance = new Maintenance();

        maintenance.setIdMaintenance(dto.getIdMaintenance());
        maintenance.setDateDebut(dto.getDateDebut());
        maintenance.setDateFin(dto.getDateFin());
        maintenance.setDescription(dto.getDescription());

        if (dto.getIdVehicule() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.getIdVehicule());
            maintenance.setVehicule(vehicule);
        }

        return maintenance;
    }
}
