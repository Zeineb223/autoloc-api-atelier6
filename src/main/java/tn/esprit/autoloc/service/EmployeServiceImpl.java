
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entity.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    public EmployeServiceImpl(IEmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe enregistrerEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> afficherEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Optional<Employe> chercherEmploye(Long id) {
        return employeRepository.findById(id);
    }

    @Override
    public void supprimerEmploye(Long id) {
        employeRepository.deleteById(id);
    }
}
